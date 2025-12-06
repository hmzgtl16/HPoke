package com.example.hpoke.core.data.repository

import com.example.hpoke.core.data.mapper.asModel
import com.example.hpoke.core.database.dao.PokemonDao
import com.example.hpoke.core.database.model.PokemonFull
import com.example.hpoke.core.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class OfflineFirstPokemonRepository : PokemonRepository, KoinComponent {

    val pokemonDao: PokemonDao by inject()

    override val pokemons: Flow<List<Pokemon>>
        get() = pokemonDao.getAllPokemon()
            .map(List<PokemonFull>::asModel)


    override suspend fun getPokemonById(id: Int): Flow<Pokemon?> =
        pokemonDao.getPokemonById(id).map { it?.asModel() }
}