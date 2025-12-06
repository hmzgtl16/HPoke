package com.example.hpoke.core.data.repository

import com.example.hpoke.core.data.mapper.asEntity
import com.example.hpoke.core.data.mapper.asModel
import com.example.hpoke.core.data.mapper.toEntity
import com.example.hpoke.core.data.sync.suspendRunCatching
import com.example.hpoke.core.database.dao.AbilityDao
import com.example.hpoke.core.database.dao.PokemonDao
import com.example.hpoke.core.database.dao.SpeciesDao
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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class OfflineFirstPokemonRepository : PokemonRepository, KoinComponent {

    val pokemonApi: PokemonApi by inject()
    val pokemonDao: PokemonDao by inject()
    val speciesDao: SpeciesDao by inject()
    val abilityDao: AbilityDao by inject()
    val typeDao: TypeDao by inject()
    val statDao: StatDao by inject()

    override val pokemons: Flow<List<Pokemon>>
        get() = pokemonDao.getAllPokemon()
            .map(List<PokemonFull>::asModel)

    override suspend fun getPokemon(id: Int): Flow<Pokemon?> =
        pokemonDao.getPokemonById(id).map { it?.asModel() }

    override suspend fun sync(): Boolean = suspendRunCatching {
        val limit = 100
        var offset = 0
        val allResults = mutableListOf<NamedApiResourceDto>()

        // paginate through the pokemon list
        do {
            val page = pokemonApi.getPokemonList(offset = offset, limit = limit)
            allResults += page.results
            offset += limit
        } while (page.next != null)

        // accumulators
        val pokemonEntities = mutableListOf<PokemonEntity>()
        val speciesEntities = mutableListOf<SpritesEntity>()
        val typeEntities = mutableListOf<TypeEntity>()
        val abilityEntities = mutableListOf<AbilityEntity>()
        val statEntities = mutableListOf<StatEntity>()
        val pokemonTypeRefs = mutableListOf<PokemonTypeCrossRef>()
        val pokemonAbilityRefs = mutableListOf<PokemonAbilityCrossRef>()
        val pokemonStatRefs = mutableListOf<PokemonStatCrossRef>()

        // iterate each pokemon summary and fetch details
        for (summary in allResults) {
            val dto = pokemonApi.getPokemon(summary.name)

            // Pokemon entity and species (species uses same id in this schema)
            val pokemonEntity = dto.toEntity()

            val spritesEntity = dto.sprites.asEntity(id = dto.id)

            pokemonEntities += pokemonEntity
            speciesEntities += spritesEntity

            // types
            dto.types.forEach {
                val typeDto = pokemonApi.getType(name = it.type.name)
                val typeEntity = typeDto.asEntity()

                typeEntities += typeEntity

                pokemonTypeRefs += PokemonTypeCrossRef(
                    pokemonId = pokemonEntity.id,
                    typeId = typeEntity.id,
                    slot = it.slot
                )
            }

            // abilities
            dto.abilities.forEach {
                val abilityDto = pokemonApi.getAbility(name = it.ability.name)
                val abilityEntity = abilityDto.asEntity()

                abilityEntities += abilityEntity

                pokemonAbilityRefs += PokemonAbilityCrossRef(
                    pokemonId = pokemonEntity.id,
                    abilityId = abilityEntity.id,
                    isHidden = it.isHidden,
                    slot = it.slot
                )
            }

            // stats
            dto.stats.forEach {
                val statDto = pokemonApi.getStat(name = it.stat.name)
                val statEntity = statDto.asEntity()

                statEntities += statEntity

                pokemonStatRefs += PokemonStatCrossRef(
                    pokemonId = dto.id,
                    statId = statEntity.id,
                    baseStat = it.baseStat,
                    effort = it.effort
                )
            }
        }

        // persist everything in bulk (DAOs use REPLACE conflict strategy)
        speciesDao.insertSpeciesList(speciesEntities.distinctBy { it.id })
        typeDao.insertTypes(typeEntities.distinctBy(TypeEntity::id))
        abilityDao.insertAbilities(abilityEntities.distinctBy(AbilityEntity::id))
        statDao.insertStats(statEntities.distinctBy(StatEntity::id))

        // pokemon and cross refs
        pokemonDao.insertPokemons(pokemonEntities)
        if (pokemonTypeRefs.isNotEmpty()) pokemonDao.insertPokemonTypeCrossRefs(pokemonTypeRefs)
        if (pokemonAbilityRefs.isNotEmpty()) pokemonDao.insertPokemonAbilityCrossRefs(
            pokemonAbilityRefs
        )
        if (pokemonStatRefs.isNotEmpty()) pokemonDao.insertPokemonStats(pokemonStatRefs)

        true
    }.isSuccess
}
