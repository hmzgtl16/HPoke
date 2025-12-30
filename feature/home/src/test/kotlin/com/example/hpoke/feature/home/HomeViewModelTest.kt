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

package com.example.hpoke.feature.home

import androidx.paging.testing.asSnapshot
import com.example.hpoke.core.testing.data.pokemonsTestData
import com.example.hpoke.core.testing.dispatcher.MainDispatcherRule
import com.example.hpoke.core.testing.repository.TestPokemonRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class HomeViewModelTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val pokemonRepository = TestPokemonRepository()
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        viewModel = HomeViewModel(pokemonRepository)
    }

    @Test
    fun `pokemons emits paging data`() =
        runTest {
            backgroundScope.launch(UnconfinedTestDispatcher()) { viewModel.pokemons.collect() }

            pokemonRepository.sendPokemons(pokemons = pokemonsTestData.subList(0, 4))

            val snapshot = viewModel.pokemons.asSnapshot()

            assertEquals(5, snapshot.size)
            assertEquals(1, snapshot[0].id)
            assertEquals(3, snapshot[2].id)
        }

    @Test
    fun `pokemons updates when repository emits new list`() =
        runTest {
            backgroundScope.launch { viewModel.pokemons.collect() }

            pokemonRepository.sendPokemons(pokemons = pokemonsTestData.subList(0, 4))

            var snapshot = viewModel.pokemons.asSnapshot()

            assertEquals(5, snapshot.size)

            pokemonRepository.sendPokemons(
                pokemons = pokemonsTestData.subList(5, 7),
            )

            snapshot = viewModel.pokemons.asSnapshot()

            assertEquals(7, snapshot.size)
            assertEquals(1, snapshot[0].id)
            assertEquals(3, snapshot[2].id)
            assertEquals(5, snapshot[6].id)
            assertEquals(6, snapshot[7].id)
        }
}
