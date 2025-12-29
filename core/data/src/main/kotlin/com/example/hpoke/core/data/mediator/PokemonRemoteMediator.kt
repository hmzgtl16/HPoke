package com.example.hpoke.core.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.hpoke.core.data.mapper.asEntity
import com.example.hpoke.core.data.mapper.asSpritesEntity
import com.example.hpoke.core.data.mapper.toEntity
import com.example.hpoke.core.database.dao.AbilityDao
import com.example.hpoke.core.database.dao.PokemonDao
import com.example.hpoke.core.database.dao.RemoteKeyDao
import com.example.hpoke.core.database.dao.SpritesDao
import com.example.hpoke.core.database.dao.StatDao
import com.example.hpoke.core.database.dao.TypeDao
import com.example.hpoke.core.database.model.PokemonAbilityCrossRef
import com.example.hpoke.core.database.model.PokemonFull
import com.example.hpoke.core.database.model.PokemonStatCrossRef
import com.example.hpoke.core.database.model.PokemonTypeCrossRef
import com.example.hpoke.core.database.model.RemoteKeyEntity
import com.example.hpoke.core.network.api.PokemonApi
import com.example.hpoke.core.network.dto.AbilityDto
import com.example.hpoke.core.network.dto.NamedApiResourceDto
import com.example.hpoke.core.network.dto.PokemonDto
import com.example.hpoke.core.network.dto.StatDto
import com.example.hpoke.core.network.dto.TypeDto
import com.example.hpoke.core.network.dto.idFromUrl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.sync.withPermit
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator : RemoteMediator<Int, PokemonFull>(), KoinComponent {

    val pokemonApi: PokemonApi by inject()
    val pokemonDao: PokemonDao by inject()
    val spritesDao: SpritesDao by inject()
    val abilityDao: AbilityDao by inject()
    val typeDao: TypeDao by inject()
    val statDao: StatDao by inject()
    val remoteKeyDao: RemoteKeyDao by inject()

    private val pageLimit = PAGE_LIMIT
    private val chunkSize = CHUNK_SIZE
    private val maxConcurrent = 10

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonFull>
    ): MediatorResult {
        val offset = try {
            when (loadType) {
                LoadType.REFRESH -> {
                    val key = getRemoteKeyClosestToCurrentPosition(state)
                    key?.nextKey?.minus(pageLimit) ?: 0
                }

                LoadType.PREPEND -> {
                    val key = getRemoteKeyForFirstItem(state)
                    val prev = key?.prevKey
                    prev ?: return MediatorResult.Success(endOfPaginationReached = key != null)
                }

                LoadType.APPEND -> {
                    val key = getRemoteKeyForLastItem(state)
                    val next = key?.nextKey
                    next ?: return MediatorResult.Success(endOfPaginationReached = key != null)
                }
            }
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }

        return try {
            val page = pokemonApi.getPokemonList(offset = offset, limit = pageLimit)
            val endReached = page.next == null || page.results.isEmpty()

            // Same idea as your function:
            // - existingIds = all pokemon ids in db
            // - needFixIds = ids with missing relations
            val existingIds = pokemonDao.getAllPokemonIds().toHashSet()
            val needFixIds = (
                    pokemonDao.pokemonIdsMissingStats() +
                            pokemonDao.pokemonIdsMissingTypes() +
                            pokemonDao.pokemonIdsMissingAbilities()
                    ).toHashSet()

            val toFetchSummaries = page.results
                .map { it to it.idFromUrl() }
                .filter { (_, id) -> id !in existingIds || id in needFixIds }
                .map(Pair<NamedApiResourceDto, Int>::first)

            if (toFetchSummaries.isEmpty()) {
                // Still need to write remote keys for paging continuity?
                // Not strictly, but nice if you want stable appends.
                return MediatorResult.Success(endOfPaginationReached = endReached)
            }

            // Concurrency: best-effort fetch
            val semaphore = kotlinx.coroutines.sync.Semaphore(maxConcurrent)
            val pokemonDtos = supervisorScope {
                toFetchSummaries.map {
                    async(Dispatchers.IO) {
                        semaphore.withPermit {
                            runCatching { pokemonApi.getPokemon(name = it.name) }
                        }
                    }
                }.awaitAll()
                    .mapNotNull(Result<PokemonDto>::getOrNull)
            }

            if (pokemonDtos.isEmpty()) {
                return MediatorResult.Success(endOfPaginationReached = endReached)
            }

            // name -> id caches (keep across this load call)
            val abilityIdByName = mutableMapOf<String, Int>()
            val statIdByName = mutableMapOf<String, Int>()
            val typeIdByName = mutableMapOf<String, Int>()


            if (loadType == LoadType.REFRESH) {
                remoteKeyDao.clearRemoteKeys()
                // Optional: if you want hard refresh:
                // pokemonDao.clearAll()
                // spritesDao.clearAll()
                // etc.
            }

            // Mark as present to prevent duplicates within same call
            pokemonDtos.forEach { existingIds += it.id }

            // --- core upserts ---
            spritesDao.insertSprites(sprites = pokemonDtos.map(PokemonDto::asSpritesEntity))
            pokemonDao.insertPokemons(pokemons = pokemonDtos.map(PokemonDto::toEntity))

            // --- ensure abilities exist (your approach) ---
            val missingAbilityNames = pokemonDtos
                .flatMap(PokemonDto::abilities)
                .map { it.ability.name }
                .distinct()
                .filterNot(abilityIdByName::containsKey)

            if (missingAbilityNames.isNotEmpty()) {
                val abilityEntities = missingAbilityNames
                    .map { pokemonApi.getAbility(name = it) }
                    .map(AbilityDto::asEntity)
                abilityDao.insertAbilities(abilities = abilityEntities)
                abilityEntities.forEach { abilityIdByName[it.name] = it.id }
            }

            // --- ensure stats exist ---
            val missingStatNames = pokemonDtos
                .flatMap(PokemonDto::stats)
                .map { it.stat.name }
                .distinct()
                .filterNot(statIdByName::containsKey)

            if (missingStatNames.isNotEmpty()) {
                val statDtos = missingStatNames
                    .map { pokemonApi.getStat(it) }
                    .map(StatDto::asEntity)
                statDao.insertStats(stats = statDtos)
                statDtos.forEach { statIdByName[it.name] = it.id }
            }

            // --- ensure types exist ---
            val missingTypeNames = pokemonDtos
                .flatMap(PokemonDto::types)
                .map { it.type.name }
                .distinct()
                .filterNot(typeIdByName::containsKey)

            if (missingTypeNames.isNotEmpty()) {
                val typeDtos = missingTypeNames
                    .map { pokemonApi.getType(name = it) }
                    .map(TypeDto::asEntity)
                typeDao.insertTypes(types = typeDtos)
                typeDtos.forEach { typeIdByName[it.name] = it.id }
            }

            // --- cross refs ---
            val abilityRefs = buildList {
                pokemonDtos.forEach { p ->
                    p.abilities.forEach { a ->
                        val id = abilityIdByName[a.ability.name] ?: return@forEach
                        add(
                            PokemonAbilityCrossRef(
                                pokemonId = p.id,
                                abilityId = id,
                                isHidden = a.isHidden,
                                slot = a.slot
                            )
                        )
                    }
                }
            }
            abilityRefs.chunked(chunkSize).forEach { pokemonDao.insertPokemonAbilityCrossRefs(it) }

            val statRefs = buildList {
                pokemonDtos.forEach { p ->
                    p.stats.forEach { s ->
                        val id = statIdByName[s.stat.name] ?: return@forEach
                        add(
                            PokemonStatCrossRef(
                                pokemonId = p.id,
                                statId = id,
                                baseStat = s.baseStat,
                                effort = s.effort
                            )
                        )
                    }
                }
            }
            statRefs.chunked(chunkSize).forEach { pokemonDao.insertPokemonStatCrossRefs(it) }

            val typeRefs = buildList {
                pokemonDtos.forEach { p ->
                    p.types.forEach { t ->
                        val id = typeIdByName[t.type.name] ?: return@forEach
                        add(
                            PokemonTypeCrossRef(
                                pokemonId = p.id,
                                typeId = id,
                                slot = t.slot
                            )
                        )
                    }
                }
            }
            typeRefs.chunked(chunkSize)
                .forEach { pokemonDao.insertPokemonTypeCrossRefs(refs = it) }

            // remote keys (offset-based)
            val prev = if (offset == 0) null else offset - pageLimit
            val next = if (endReached) null else offset + pageLimit
            val keys = pokemonDtos.map {
                RemoteKeyEntity(pokemonId = it.id, prevKey = prev, nextKey = next)
            }
            remoteKeyDao.insertAll(keys = keys)

            MediatorResult.Success(endOfPaginationReached = endReached)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, PokemonFull>
    ): RemoteKeyEntity? {
        val last = state.lastItemOrNull() ?: return null
        val id = last.pokemon.id
        return remoteKeyDao.remoteKeyPokemonId(id)
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, PokemonFull>
    ): RemoteKeyEntity? {
        val first = state.firstItemOrNull() ?: return null
        val id = first.pokemon.id
        return remoteKeyDao.remoteKeyPokemonId(id)
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, PokemonFull>
    ): RemoteKeyEntity? {
        val pos = state.anchorPosition ?: return null
        val closest = state.closestItemToPosition(pos) ?: return null
        val id = closest.pokemon.id
        return remoteKeyDao.remoteKeyPokemonId(id)
    }

    companion object {
        private const val PAGE_LIMIT = 20
        private const val CHUNK_SIZE = 25
    }
}