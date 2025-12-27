package com.example.hpoke.core.testing.repository

import androidx.paging.PagingData
import com.example.hpoke.core.data.repository.PokemonRepository
import com.example.hpoke.core.model.Pokemon
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map

class TestPokemonRepository : PokemonRepository {

    private val pokemonsFlow: MutableSharedFlow<List<Pokemon>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun getPokemons(pageSize: Int): Flow<PagingData<Pokemon>> =
        pokemonsFlow.map { PagingData.from(it) }

    override fun getPokemon(id: Int): Flow<Pokemon> =
        pokemonsFlow.map {
            it.first { pokemon -> pokemon.id == id }
        }

    override suspend fun sync(): Boolean = true

    fun sendPokemons(pokemons: List<Pokemon>) {
        pokemonsFlow.tryEmit(pokemons)
    }
}