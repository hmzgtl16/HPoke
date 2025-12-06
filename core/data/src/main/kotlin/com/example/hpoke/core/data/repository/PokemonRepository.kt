package com.example.hpoke.core.data.repository

import com.example.hpoke.core.data.sync.Syncable
import com.example.hpoke.core.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository : Syncable {

    val pokemons: Flow<List<Pokemon>>

    suspend fun getPokemon(id: Int): Flow<Pokemon?>
}