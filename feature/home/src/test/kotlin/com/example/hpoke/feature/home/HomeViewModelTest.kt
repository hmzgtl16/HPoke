package com.example.hpoke.feature.home

import androidx.paging.testing.asSnapshot
import com.example.hpoke.core.model.Ability
import com.example.hpoke.core.model.Pokemon
import com.example.hpoke.core.model.Species
import com.example.hpoke.core.model.Stat
import com.example.hpoke.core.model.Type
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
    fun `pokemons emits paging data`() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher()) { viewModel.pokemons.collect() }

        pokemonRepository.sendPokemons(pokemons = testPokemons)

        val snapshot = viewModel.pokemons.asSnapshot()

        assertEquals(5, snapshot.size)
        assertEquals(1, snapshot[0].id)
        assertEquals(3, snapshot[2].id)
    }

    @Test
    fun `pokemons updates when repository emits new list`() = runTest {
        backgroundScope.launch { viewModel.pokemons.collect() }

        pokemonRepository.sendPokemons(pokemons = testPokemons)

        var snapshot = viewModel.pokemons.asSnapshot()

        assertEquals(5, snapshot.size)

        pokemonRepository.sendPokemons(
            pokemons = listOf(
                createTestPokemon(id = 6, name = "Squirtle"),
                createTestPokemon(id = 7, name = "Wartortle")
            )
        )

        snapshot = viewModel.pokemons.asSnapshot()

        assertEquals(7, snapshot.size)
        assertEquals(1, snapshot[0].id)
        assertEquals(3, snapshot[2].id)
        assertEquals(5, snapshot[6].id)
        assertEquals(6, snapshot[7].id)
    }
}

private val testPokemons = listOf(
    createTestPokemon(id = 1, name = "Bulbasaur"),
    createTestPokemon(id = 2, name = "Ivysaur"),
    createTestPokemon(id = 3, name = "Venusaur"),
    createTestPokemon(id = 4, name = "Charmander"),
    createTestPokemon(id = 5, name = "Charmeleon")
)

private fun createTestPokemon(
    id: Int,
    name: String,
    types: List<Type> = listOf(Type(slot = 1, id = 1, name = "normal"))
): Pokemon {
    return Pokemon(
        id = id,
        name = name,
        height = 10,
        weight = 100,
        baseExperience = 100,
        species = Species(
            id = id,
            frontDefault = "https://example.com/$id.png"
        ),
        types = types,
        stats = listOf(
            Stat(id = 1, name = "hp", baseStat = 45, effort = 0),
            Stat(id = 2, name = "attack", baseStat = 49, effort = 0),
            Stat(id = 3, name = "defense", baseStat = 49, effort = 0),
            Stat(id = 4, name = "sp-atk", baseStat = 65, effort = 1),
            Stat(id = 5, name = "sp-def", baseStat = 65, effort = 0),
            Stat(id = 6, name = "speed", baseStat = 45, effort = 0)
        ),
        abilities = listOf(
            Ability(id = 1, name = "overgrow", isHidden = false, slot = 1)
        )
    )
}