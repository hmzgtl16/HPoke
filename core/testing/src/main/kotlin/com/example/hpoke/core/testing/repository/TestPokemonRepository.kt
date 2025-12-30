/*
 * Copyright (C) 2025 Hamza Gattal
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

    fun sendPokemons(pokemons: List<Pokemon>) {
        pokemonsFlow.tryEmit(pokemons)
    }
}
