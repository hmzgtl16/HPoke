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

package com.example.hpoke.core.database.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.hpoke.core.database.DatabaseTest
import com.example.hpoke.core.database.model.AbilityEntity
import com.example.hpoke.core.database.model.PokemonAbilityCrossRef
import com.example.hpoke.core.database.model.PokemonEntity
import com.example.hpoke.core.database.model.PokemonStatCrossRef
import com.example.hpoke.core.database.model.PokemonTypeCrossRef
import com.example.hpoke.core.database.model.SpritesEntity
import com.example.hpoke.core.database.model.StatEntity
import com.example.hpoke.core.database.model.TypeEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class DaosTest : DatabaseTest() {
    @Test
    fun testInsertSinglePokemon() =
        runTest {
            val sprites =
                SpritesEntity(
                    id = 1,
                    frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                    frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/1.png",
                )
            val pokemon =
                PokemonEntity(
                    id = 1,
                    name = "Bulbasaur",
                    height = 7,
                    weight = 69,
                    baseExperience = 64,
                    spritesId = 1,
                )
            spritesDao.insertSprites(sprites = listOf(element = sprites))
            pokemonDao.insertPokemons(pokemons = listOf(element = pokemon))
            pokemonDao.getAllPokemonFlow().first {
                assertEquals(1, it.size)
                assertEquals("Bulbasaur", it[0].pokemon.name)
                true
            }
        }

    @Test
    fun testInsertPokemonWithNullValues() =
        runTest {
            val sprites =
                SpritesEntity(
                    id = 1,
                    frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                    frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/1.png",
                )
            val pokemon =
                PokemonEntity(
                    id = 1,
                    name = "Bulbasaur",
                    height = null,
                    weight = null,
                    baseExperience = null,
                    spritesId = 1,
                )
            spritesDao.insertSprites(sprites = listOf(element = sprites))
            pokemonDao.insertPokemons(listOf(pokemon))
            pokemonDao.getPokemonById(1).first {
                assertNotNull(it)
                assertEquals(null, it.pokemon.height)
                assertEquals(null, it.pokemon.weight)
                assertEquals(null, it.pokemon.baseExperience)
                true
            }
        }

    // ========== INSERT MULTIPLE POKEMONS ==========

    @Test
    fun testInsertMultiplePokemons() =
        runTest {
            val sprites =
                listOf(
                    SpritesEntity(
                        id = 1,
                        frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                        frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/1.png",
                    ),
                    SpritesEntity(
                        id = 2,
                        frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png",
                        frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/2.png",
                    ),
                    SpritesEntity(
                        id = 3,
                        frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png",
                        frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/3.png",
                    ),
                )
            val pokemons =
                listOf(
                    PokemonEntity(
                        id = 1,
                        name = "Bulbasaur",
                        height = 7,
                        weight = 69,
                        baseExperience = 64,
                        spritesId = 1,
                    ),
                    PokemonEntity(
                        id = 2,
                        name = "Ivysaur",
                        height = 10,
                        weight = 130,
                        baseExperience = 142,
                        spritesId = 2,
                    ),
                    PokemonEntity(
                        id = 3,
                        name = "Venusaur",
                        height = 20,
                        weight = 1000,
                        baseExperience = 236,
                        spritesId = 3,
                    ),
                )
            spritesDao.insertSprites(sprites = sprites)
            pokemonDao.insertPokemons(pokemons = pokemons)
            pokemonDao.getAllPokemonFlow().first {
                assertEquals(3, it.size)
                assertEquals("Bulbasaur", it[0].pokemon.name)
                assertEquals("Ivysaur", it[1].pokemon.name)
                assertEquals("Venusaur", it[2].pokemon.name)
                true
            }
        }

    @Test
    fun testInsertMultiplePokemonsWithNullFields() =
        runTest {
            val sprites =
                listOf(
                    SpritesEntity(
                        id = 1,
                        frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                        frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/1.png",
                    ),
                    SpritesEntity(
                        id = 2,
                        frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png",
                        frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/2.png",
                    ),
                    SpritesEntity(
                        id = 3,
                        frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png",
                        frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/3.png",
                    ),
                )
            val pokemons =
                listOf(
                    PokemonEntity(
                        id = 1,
                        name = "Bulbasaur",
                        height = 7,
                        weight = 69,
                        baseExperience = null,
                        spritesId = null,
                    ),
                    PokemonEntity(
                        id = 2,
                        name = "Ivysaur",
                        height = null,
                        weight = 130,
                        baseExperience = 142,
                        spritesId = null,
                    ),
                    PokemonEntity(
                        id = 3,
                        name = "Venusaur",
                        height = 20,
                        weight = null,
                        baseExperience = null,
                        spritesId = null,
                    ),
                )
            spritesDao.insertSprites(sprites = sprites)
            pokemonDao.insertPokemons(pokemons = pokemons)
            pokemonDao.getAllPokemonFlow().first {
                assertEquals(3, it.size)
                assertEquals(null, it[0].pokemon.baseExperience)
                assertEquals(null, it[1].pokemon.height)
                assertEquals(null, it[2].pokemon.weight)
                true
            }
        }

    // ========== GET POKEMON BY ID ==========

    @Test
    fun testGetPokemonById() =
        runTest {
            val sprites =
                SpritesEntity(
                    id = 1,
                    frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                    frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/1.png",
                )
            val pokemon =
                PokemonEntity(
                    id = 1,
                    name = "Bulbasaur",
                    height = 7,
                    weight = 69,
                    baseExperience = 64,
                    spritesId = null,
                )
            spritesDao.insertSprites(sprites = listOf(sprites))
            pokemonDao.insertPokemons(pokemons = listOf(pokemon))
            pokemonDao.getPokemonById(1).first {
                assertNotNull(it)
                assertEquals(1, it.pokemon.id)
                assertEquals("Bulbasaur", it.pokemon.name)
                true
            }
        }

    @Test
    fun testGetPokemonByIdNotFound() =
        runTest {
            pokemonDao.getPokemonById(id = 999).first {
                assertEquals(null, it)
                true
            }
        }

    @Test
    fun testGetPokemonByIdMultipleTimes() =
        runTest {
            val sprites =
                SpritesEntity(
                    id = 1,
                    frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                    frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/1.png",
                )
            val pokemon =
                PokemonEntity(
                    id = 1,
                    name = "Bulbasaur",
                    height = 7,
                    weight = 69,
                    baseExperience = 64,
                    spritesId = null,
                )
            spritesDao.insertSprites(sprites = listOf(sprites))
            pokemonDao.insertPokemons(pokemons = listOf(pokemon))

            // Query multiple times
            pokemonDao.getPokemonById(1).first {
                assertNotNull(it)
                assertEquals(1, it.pokemon.id)
                true
            }

            pokemonDao.getPokemonById(1).first {
                assertNotNull(it)
                assertEquals(1, it.pokemon.id)
                true
            }
        }

    // ========== GET ALL POKEMONS ==========

    @Test
    fun testGetAllPokemons() =
        runTest {
            val sprites =
                listOf(
                    SpritesEntity(
                        id = 1,
                        frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                        frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/1.png",
                    ),
                    SpritesEntity(
                        id = 2,
                        frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png",
                        frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/2.png",
                    ),
                    SpritesEntity(
                        id = 3,
                        frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png",
                        frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/3.png",
                    ),
                )
            val pokemons =
                listOf(
                    PokemonEntity(
                        id = 1,
                        name = "Bulbasaur",
                        height = 7,
                        weight = 69,
                        baseExperience = 64,
                        spritesId = 1,
                    ),
                    PokemonEntity(
                        id = 2,
                        name = "Ivysaur",
                        height = 10,
                        weight = 130,
                        baseExperience = 142,
                        spritesId = 2,
                    ),
                    PokemonEntity(
                        id = 3,
                        name = "Venusaur",
                        height = 20,
                        weight = 1000,
                        baseExperience = 236,
                        spritesId = 3,
                    ),
                )
            spritesDao.insertSprites(sprites = sprites)
            pokemonDao.insertPokemons(pokemons = pokemons)
            pokemonDao.getAllPokemonFlow().first {
                assertEquals(3, it.size)
                true
            }
        }

    @Test
    fun testGetAllPokemonsEmpty() =
        runTest {
            pokemonDao.getAllPokemonFlow().first {
                assertEquals(0, it.size)
                true
            }
        }

    // ========== REPLACE POKEMONS ==========

    @Test
    fun testReplacePokemonWithSameId() =
        runTest {
            val sprites =
                SpritesEntity(
                    id = 1,
                    frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                    frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/1.png",
                )
            val pokemon1 =
                PokemonEntity(
                    id = 1,
                    name = "Bulbasaur",
                    height = 7,
                    weight = 69,
                    baseExperience = 64,
                    spritesId = 1,
                )
            val pokemon2 =
                PokemonEntity(
                    id = 1,
                    name = "Bulbasaur",
                    height = 10,
                    weight = 75,
                    baseExperience = 70,
                    spritesId = 1,
                )
            spritesDao.insertSprites(sprites = listOf(sprites))
            pokemonDao.insertPokemons(pokemons = listOf(pokemon1))
            pokemonDao.insertPokemons(pokemons = listOf(pokemon2))

            pokemonDao.getAllPokemonFlow().first {
                assertEquals(1, it.size)
                assertEquals(10, it[0].pokemon.height)
                assertEquals(75, it[0].pokemon.weight)
                assertEquals(70, it[0].pokemon.baseExperience)
                true
            }
        }

    // ========== INSERT POKEMON WITH TYPES ==========

    @Test
    fun testInsertPokemonWithSingleType() =
        runTest {
            val sprites =
                SpritesEntity(
                    id = 25,
                    frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png",
                    frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/25.png",
                )
            val pokemon =
                PokemonEntity(
                    id = 25,
                    name = "Pikachu",
                    height = 4,
                    weight = 60,
                    baseExperience = 112,
                    spritesId = 25,
                )
            val type =
                TypeEntity(
                    id = 13,
                    name = "Electric",
                )
            spritesDao.insertSprites(sprites = listOf(sprites))
            pokemonDao.insertPokemons(pokemons = listOf(pokemon))
            typeDao.insertTypes(types = listOf(type))

            pokemonDao.insertPokemonTypeCrossRefs(listOf(PokemonTypeCrossRef(25, 13, 1)))

            pokemonDao.getPokemonById(25).first {
                assertNotNull(it)
                assertEquals(1, it.types.size)
                assertEquals("Electric", it.types[0].type.name)
                true
            }
        }

    @Test
    fun testInsertPokemonWithMultipleTypes() =
        runTest {
            val sprites =
                SpritesEntity(
                    id = 55,
                    frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                    frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/1.png",
                )
            val pokemon =
                PokemonEntity(
                    id = 55,
                    name = "Bulbasaur",
                    height = 7,
                    weight = 69,
                    baseExperience = 64,
                    spritesId = 55,
                )
            val types =
                listOf(
                    TypeEntity(
                        id = 12,
                        name = "Grass",
                    ),
                    TypeEntity(
                        id = 4,
                        name = "Poison",
                    ),
                )

            spritesDao.insertSprites(sprites = listOf(sprites))
            pokemonDao.insertPokemons(pokemons = listOf(pokemon))
            typeDao.insertTypes(types = types)
            pokemonDao.insertPokemonTypeCrossRefs(
                refs =
                    listOf(
                        PokemonTypeCrossRef(pokemonId = 55, typeId = 12, slot = 1),
                        PokemonTypeCrossRef(pokemonId = 55, typeId = 4, slot = 2),
                    ),
            )

            pokemonDao.getPokemonById(55).first {
                assertNotNull(it)
                assertEquals(2, it.types.size)
                assertEquals(
                    "Grass",
                    it.types.first { item -> item.type.id == 12 }.type.name,
                )
                assertEquals(
                    "Poison",
                    it.types.first { item -> item.type.id == 4 }.type.name,
                )
                assertEquals(
                    1,
                    it.types.first { item -> item.type.id == 12 }.pokemonTypeCrossRef.slot,
                )
                assertEquals(
                    2,
                    it.types.first { item -> item.type.id == 4 }.pokemonTypeCrossRef.slot,
                )
                true
            }
        }

    // ========== INSERT POKEMON WITH ABILITIES ==========

    @Test
    fun testInsertPokemonWithSingleAbility() =
        runTest {
            val sprites =
                SpritesEntity(
                    id = 1,
                    frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                    frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/1.png",
                )
            val pokemon =
                PokemonEntity(
                    id = 1,
                    name = "Bulbasaur",
                    height = 7,
                    weight = 69,
                    baseExperience = 64,
                    spritesId = 1,
                )
            val ability =
                AbilityEntity(
                    id = 65,
                    name = "Overgrow",
                )

            spritesDao.insertSprites(sprites = listOf(sprites))
            pokemonDao.insertPokemons(listOf(pokemon))
            abilityDao.insertAbilities(listOf(ability))
            pokemonDao.insertPokemonAbilityCrossRefs(
                listOf(
                    element =
                        PokemonAbilityCrossRef(
                            pokemonId = 1,
                            abilityId = 65,
                            isHidden = false,
                            slot = 1,
                        ),
                ),
            )

            pokemonDao.getPokemonById(1).first {
                assertNotNull(it)
                assertEquals(1, it.abilities.size)
                assertEquals("Overgrow", it.abilities[0].ability.name)
                true
            }
        }

    @Test
    fun testInsertPokemonWithMultipleAbilities() =
        runTest {
            val sprites =
                SpritesEntity(
                    id = 1,
                    frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                    frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/1.png",
                )
            val pokemon =
                PokemonEntity(
                    id = 1,
                    name = "Bulbasaur",
                    height = 7,
                    weight = 69,
                    baseExperience = 64,
                    spritesId = 1,
                )
            val abilities =
                listOf(
                    AbilityEntity(
                        id = 65,
                        name = "Overgrow",
                    ),
                    AbilityEntity(
                        id = 34,
                        name = "Chlorophyll",
                    ),
                )

            spritesDao.insertSprites(sprites = listOf(sprites))
            pokemonDao.insertPokemons(pokemons = listOf(pokemon))
            abilityDao.insertAbilities(abilities = abilities)
            pokemonDao.insertPokemonAbilityCrossRefs(
                refs =
                    listOf(
                        PokemonAbilityCrossRef(
                            pokemonId = 1,
                            abilityId = 65,
                            isHidden = false,
                            slot = 1,
                        ),
                        PokemonAbilityCrossRef(
                            pokemonId = 1,
                            abilityId = 34,
                            isHidden = true,
                            slot = 2,
                        ),
                    ),
            )

            pokemonDao.getPokemonById(1).first {
                assertNotNull(it)
                assertEquals(2, it.abilities.size)
                assertEquals(
                    "Overgrow",
                    it.abilities.first { item -> item.ability.id == 65 }.ability.name,
                )
                assertEquals(
                    "Chlorophyll",
                    it.abilities.first { item -> item.ability.id == 34 }.ability.name,
                )
                assertFalse(
                    it.abilities.first { item -> item.ability.id == 65 }.pokemonAbilityCrossRef.isHidden,
                )
                assertTrue(
                    it.abilities.first { item -> item.ability.id == 34 }.pokemonAbilityCrossRef.isHidden,
                )
                true
            }
        }

    // ========== INSERT POKEMON WITH STATS ==========

    @Test
    fun testInsertPokemonWithAllStats() =
        runTest {
            val sprites =
                SpritesEntity(
                    id = 1,
                    frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                    frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/1.png",
                )
            val pokemon =
                PokemonEntity(
                    id = 1,
                    name = "Bulbasaur",
                    height = 7,
                    weight = 69,
                    baseExperience = 64,
                    spritesId = 1,
                )
            val stats =
                listOf(
                    StatEntity(id = 1, name = "HP"),
                    StatEntity(id = 2, name = "Attack"),
                    StatEntity(id = 3, name = "Defense"),
                    StatEntity(id = 4, name = "Sp. Atk"),
                    StatEntity(id = 5, name = "Sp. Def"),
                    StatEntity(id = 6, name = "Speed"),
                )

            spritesDao.insertSprites(sprites = listOf(sprites))
            pokemonDao.insertPokemons(pokemons = listOf(pokemon))
            statDao.insertStats(stats = stats)
            pokemonDao.insertPokemonStatCrossRefs(
                refs =
                    listOf(
                        PokemonStatCrossRef(pokemonId = 1, statId = 1, baseStat = 45, effort = 0),
                        PokemonStatCrossRef(pokemonId = 1, statId = 2, baseStat = 49, effort = 0),
                        PokemonStatCrossRef(pokemonId = 1, statId = 3, baseStat = 49, effort = 0),
                        PokemonStatCrossRef(pokemonId = 1, statId = 4, baseStat = 65, effort = 1),
                        PokemonStatCrossRef(pokemonId = 1, statId = 5, baseStat = 65, effort = 1),
                        PokemonStatCrossRef(pokemonId = 1, statId = 6, baseStat = 45, effort = 0),
                    ),
            )

            pokemonDao.getPokemonById(1).first {
                assertNotNull(it)
                assertEquals(6, it.stats.size)
                assertEquals(45, it.stats[0].pokemonStatCrossRef.baseStat)
                assertEquals(49, it.stats[1].pokemonStatCrossRef.baseStat)
                assertEquals(49, it.stats[2].pokemonStatCrossRef.baseStat)
                true
            }
        }
}
