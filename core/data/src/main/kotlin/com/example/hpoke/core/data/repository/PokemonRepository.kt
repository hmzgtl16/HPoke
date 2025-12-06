package com.example.hpoke.core.data.repository

import com.example.hpoke.core.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    val pokemons: Flow<List<Pokemon>>

    suspend fun getPokemonById(id: Int): Flow<Pokemon?>
}