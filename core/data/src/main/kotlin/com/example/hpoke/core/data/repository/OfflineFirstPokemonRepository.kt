package com.example.hpoke.core.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.hpoke.core.data.mapper.asEntity
import com.example.hpoke.core.data.mapper.asModel
import com.example.hpoke.core.data.mapper.asSpritesEntity
import com.example.hpoke.core.data.mapper.toEntity
import com.example.hpoke.core.data.sync.suspendRunCatching
import com.example.hpoke.core.database.dao.AbilityDao
import com.example.hpoke.core.database.dao.PokemonDao
import com.example.hpoke.core.database.dao.SpritesDao
import com.example.hpoke.core.database.dao.StatDao
import com.example.hpoke.core.database.dao.TypeDao
import com.example.hpoke.core.database.model.PokemonAbilityCrossRef
import com.example.hpoke.core.database.model.PokemonFull
import com.example.hpoke.core.database.model.PokemonStatCrossRef
import com.example.hpoke.core.database.model.PokemonTypeCrossRef
import com.example.hpoke.core.model.Pokemon
import com.example.hpoke.core.network.api.PokemonApi
import com.example.hpoke.core.network.dto.AbilityDto
import com.example.hpoke.core.network.dto.PokemonDto
import com.example.hpoke.core.network.dto.StatDto
import com.example.hpoke.core.network.dto.TypeDto
import com.example.hpoke.core.network.dto.idFromUrl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class OfflineFirstPokemonRepository : PokemonRepository, KoinComponent {

    val pokemonApi: PokemonApi by inject()
    val pokemonDao: PokemonDao by inject()
    val spritesDao: SpritesDao by inject()
    val abilityDao: AbilityDao by inject()
    val typeDao: TypeDao by inject()
    val statDao: StatDao by inject()

    override fun getPokemons(pageSize: Int): Flow<PagingData<Pokemon>> =
        Pager(
            config = PagingConfig(pageSize = pageSize),
            pagingSourceFactory = pokemonDao::getAllPokemon
        )
            .flow
            .map(PagingData<PokemonFull>::asModel)

    override fun getPokemon(id: Int): Flow<Pokemon> =
        pokemonDao.getPokemonById(id = id)
            .filterNotNull()
            .map(PokemonFull::asModel)

    override suspend fun sync(): Boolean = suspendRunCatching {
        syncIncrementalAndFixIncomplete()
    }.onFailure {
        Log.e("PokemonSync", "Sync failed", it)
    }.isSuccess

    private suspend fun syncIncrementalAndFixIncomplete() {

        // 1) Existing pokemon ids
        val existingIds = pokemonDao.getAllPokemonIds().toHashSet()

        // 2) Pokemon ids that exist but have missing relations
        val needFixIds = (
                pokemonDao.pokemonIdsMissingStats() +
                        pokemonDao.pokemonIdsMissingTypes() +
                        pokemonDao.pokemonIdsMissingAbilities()
                ).toHashSet()

        // name -> id caches for related entities
        val abilityIdByName = mutableMapOf<String, Int>()
        val statIdByName = mutableMapOf<String, Int>()
        val typeIdByName = mutableMapOf<String, Int>()

        var offset = 0
        while (true) {
            val page = pokemonApi.getPokemonList(offset = offset, limit = PAGE_LIMIT)

            // Fetch details only for:
            // - missing pokemons
            // - OR existing but incomplete ones
            val toFetch = page.results
                .map { it to it.idFromUrl() }
                .filter { (_, id) -> id !in existingIds || id in needFixIds }
                .map { (res, _) -> res }

            toFetch.chunked(CHUNK_SIZE).forEach { chunk ->
                // best-effort per pokemon (do not abort whole sync)
                val pokemonDtos = chunk.map {
                    pokemonApi.getPokemon(name = it.name)
                }

                if (pokemonDtos.isEmpty()) return@forEach

                // Mark as present to prevent duplicates during same run
                pokemonDtos.forEach { existingIds += it.id }

                // --- core upserts ---
                spritesDao.insertSprites(pokemonDtos.map(PokemonDto::asSpritesEntity))
                pokemonDao.insertPokemons(pokemonDtos.map(PokemonDto::toEntity))

                // --- ensure abilities exist ---
                val missingAbilityNames = pokemonDtos
                    .flatMap(PokemonDto::abilities)
                    .map { it.ability.name }
                    .distinct()
                    .filterNot(abilityIdByName::containsKey)

                if (missingAbilityNames.isNotEmpty()) {
                    val abilityDtos = missingAbilityNames.map {
                        pokemonApi.getAbility(it)
                    }

                    abilityDao.insertAbilities(abilityDtos.map(AbilityDto::asEntity))
                    abilityDtos.forEach { abilityIdByName[it.name] = it.id }
                }

                // --- ensure stats exist ---
                val missingStatNames = pokemonDtos
                    .flatMap(PokemonDto::stats)
                    .map { it.stat.name }
                    .distinct()
                    .filterNot(statIdByName::containsKey)

                if (missingStatNames.isNotEmpty()) {
                    val statDtos = missingStatNames.map {
                        pokemonApi.getStat(it)
                    }
                    statDao.insertStats(statDtos.map(StatDto::asEntity))
                    statDtos.forEach { statIdByName[it.name] = it.id }
                }

                // --- ensure types exist ---
                val missingTypeNames = pokemonDtos
                    .flatMap(PokemonDto::types)
                    .map { it.type.name }
                    .distinct()
                    .filterNot(typeIdByName::containsKey)

                if (missingTypeNames.isNotEmpty()) {
                    val typeDtos = missingTypeNames.map {
                        pokemonApi.getType(it)
                    }
                    typeDao.insertTypes(typeDtos.map(TypeDto::asEntity))
                    typeDtos.forEach { typeIdByName[it.name] = it.id }
                }

                // --- cross refs (only add if the id exists) ---
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
                abilityRefs
                    .chunked(CHUNK_SIZE)
                    .forEach { pokemonDao.insertPokemonAbilityCrossRefs(refs = it) }

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
                statRefs
                    .chunked(CHUNK_SIZE)
                    .forEach { pokemonDao.insertPokemonStatCrossRefs(refs = it) }

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
                typeRefs
                    .chunked(CHUNK_SIZE)
                    .forEach { pokemonDao.insertPokemonTypeCrossRefs(refs = it) }

                // once fixed, remove from needFixIds (so we don't refetch again)
                pokemonDtos.forEach { needFixIds.remove(it.id) }
            }

            offset += PAGE_LIMIT
            if (page.next == null) break
        }
    }

    companion object {
        private const val CHUNK_SIZE = 50
        private const val PAGE_LIMIT = 50
    }
}
