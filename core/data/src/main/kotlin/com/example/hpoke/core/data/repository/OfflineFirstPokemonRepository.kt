package com.example.hpoke.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.hpoke.core.data.mapper.asEntity
import com.example.hpoke.core.data.mapper.asModel
import com.example.hpoke.core.data.mapper.toEntity
import com.example.hpoke.core.data.sync.suspendRunCatching
import com.example.hpoke.core.database.dao.AbilityDao
import com.example.hpoke.core.database.dao.PokemonDao
import com.example.hpoke.core.database.dao.SpritesDao
import com.example.hpoke.core.database.dao.StatDao
import com.example.hpoke.core.database.dao.TypeDao
import com.example.hpoke.core.database.model.AbilityEntity
import com.example.hpoke.core.database.model.PokemonAbilityCrossRef
import com.example.hpoke.core.database.model.PokemonEntity
import com.example.hpoke.core.database.model.PokemonFull
import com.example.hpoke.core.database.model.PokemonStatCrossRef
import com.example.hpoke.core.database.model.PokemonTypeCrossRef
import com.example.hpoke.core.database.model.SpritesEntity
import com.example.hpoke.core.database.model.StatEntity
import com.example.hpoke.core.database.model.TypeEntity
import com.example.hpoke.core.model.Pokemon
import com.example.hpoke.core.network.api.PokemonApi
import com.example.hpoke.core.network.dto.NamedApiResourceDto
import com.example.hpoke.core.network.dto.PokemonDto
import com.example.hpoke.core.network.dto.PokemonListDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import kotlinx.coroutines.withContext
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



    override suspend fun getPokemon(id: Int): Flow<Pokemon?> =
        pokemonDao.getPokemonById(id).map { it?.asModel() }

    override suspend fun sync(): Boolean = suspendRunCatching {

        // tuning knobs
        val pageLimit = 100
        val chunkSize = 100
        val maxConcurrent = 3

        // 1) Fetch all pokemon summaries (names)
        val allResults = mutableListOf<NamedApiResourceDto>()
        var offset = 0
        var page: PokemonListDto

        do {
            page = pokemonApi.getPokemonList(offset = offset, limit = pageLimit)
            allResults += page.results
            offset += pageLimit
        } while (page.next != null)

        // 2) Caches to avoid repeated calls for shared resources
        val typeCache = mutableMapOf<String, TypeEntity>()
        val abilityCache = mutableMapOf<String, AbilityEntity>()
        val statCache = mutableMapOf<String, StatEntity>()

        val semaphore = Semaphore(maxConcurrent)

        // 3) Process in chunks to reduce RAM footprint
        val chunks = allResults.chunked(chunkSize)
        chunks.forEachIndexed { chunkIndex, chunk ->

            // ---- Accumulators per chunk ----
            val pokemonEntities = mutableListOf<PokemonEntity>()
            val spritesEntities = mutableListOf<SpritesEntity>()

            val typeEntities = mutableListOf<TypeEntity>()
            val abilityEntities = mutableListOf<AbilityEntity>()
            val statEntities = mutableListOf<StatEntity>()

            val pokemonTypeRefs = mutableListOf<PokemonTypeCrossRef>()
            val pokemonAbilityRefs = mutableListOf<PokemonAbilityCrossRef>()
            val pokemonStatRefs = mutableListOf<PokemonStatCrossRef>()

            val results = supervisorScope {
                chunk.map { summary ->
                    async(Dispatchers.IO) {
                        semaphore.withPermit {
                            runCatching {
                                pokemonApi.getPokemon(summary.name)
                            }
                        }
                    }
                }.awaitAll()
            }

            val pokemonDtos = results
                .mapNotNull(Result<PokemonDto>::getOrNull)

            // Build entities + cross refs
            for (dto in pokemonDtos) {
                val pokemonEntity = dto.toEntity()
                pokemonEntities += pokemonEntity
                spritesEntities += dto.sprites.asEntity(id = dto.id)

                // types (cached)
                dto.types.forEach { t ->
                    val typeName = t.type.name
                    val typeEntity = typeCache[typeName] ?: run {
                        val fetched = semaphore.withPermit {
                            pokemonApi.getType(name = typeName)
                        }.asEntity()
                        typeCache[typeName] = fetched
                        fetched
                    }

                    typeEntities += typeEntity
                    pokemonTypeRefs += PokemonTypeCrossRef(
                        pokemonId = pokemonEntity.id,
                        typeId = typeEntity.id,
                        slot = t.slot
                    )
                }

                // abilities (cached)
                dto.abilities.forEach { a ->
                    val abilityName = a.ability.name
                    val abilityEntity = abilityCache[abilityName] ?: run {
                        val fetched = semaphore.withPermit {
                            pokemonApi.getAbility(name = abilityName)
                        }.asEntity()
                        abilityCache[abilityName] = fetched
                        fetched
                    }

                    abilityEntities += abilityEntity
                    pokemonAbilityRefs += PokemonAbilityCrossRef(
                        pokemonId = pokemonEntity.id,
                        abilityId = abilityEntity.id,
                        isHidden = a.isHidden,
                        slot = a.slot
                    )
                }

                // stats (cached)
                dto.stats.forEach { s ->
                    val statName = s.stat.name
                    val statEntity = statCache[statName] ?: run {
                        val fetched = semaphore.withPermit {
                            pokemonApi.getStat(name = statName)
                        }.asEntity()
                        statCache[statName] = fetched
                        fetched
                    }

                    statEntities += statEntity
                    pokemonStatRefs += PokemonStatCrossRef(
                        pokemonId = pokemonEntity.id,
                        statId = statEntity.id,
                        baseStat = s.baseStat,
                        effort = s.effort
                    )
                }
            }

            // 4) Persist this chunk atomically
            withContext(Dispatchers.IO) {

                // Shared tables (dedupe inside chunk + REPLACE at DAO level)
                spritesDao.insertSprites(spritesEntities.distinctBy { it.id })
                typeDao.insertTypes(typeEntities.distinctBy(TypeEntity::id))
                abilityDao.insertAbilities(abilityEntities.distinctBy(AbilityEntity::id))
                statDao.insertStats(statEntities.distinctBy(StatEntity::id))

                // Pokemon + cross refs
                pokemonDao.insertPokemons(pokemonEntities)

                if (pokemonTypeRefs.isNotEmpty())
                    pokemonDao.insertPokemonTypeCrossRefs(pokemonTypeRefs)
                if (pokemonAbilityRefs.isNotEmpty())
                    pokemonDao.insertPokemonAbilityCrossRefs(pokemonAbilityRefs)
                if (pokemonStatRefs.isNotEmpty())
                    pokemonDao.insertPokemonStats(pokemonStatRefs)
            }


        }

        true
    }.isSuccess
}
