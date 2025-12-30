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

package com.example.hpoke.core.network.api

import com.example.hpoke.core.network.dto.AbilityDto
import com.example.hpoke.core.network.dto.NamedApiResourceListDto
import com.example.hpoke.core.network.dto.PokemonDto
import com.example.hpoke.core.network.dto.StatDto
import com.example.hpoke.core.network.dto.TypeDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class PokemonApiTest {
    private lateinit var pokemonApi: PokemonApi
    private lateinit var mockHttpClient: HttpClient

    private fun createMockHttpClient(responseBody: String): HttpClient {
        val mockEngine =
            MockEngine { _ ->
                respond(
                    content = ByteReadChannel(responseBody),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json"),
                )
            }

        return HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    },
                )
            }
        }
    }

    private fun createPokemonApiWithMockClient(client: HttpClient): PokemonApi =
        object : PokemonApi {
            override suspend fun getPokemonList(offset: Int, limit: Int) =
                client.get("pokemon") {
                    parameter("offset", offset)
                    parameter("limit", limit)
                }.body<NamedApiResourceListDto>()

            override suspend fun getPokemon(name: String) =
                client.get("pokemon/$name").body<PokemonDto>()

            override suspend fun getAbility(name: String) =
                client.get("ability/$name").body<AbilityDto>()

            override suspend fun getStat(name: String) = client.get("stat/$name").body<StatDto>()

            override suspend fun getType(name: String) = client.get("type/$name").body<TypeDto>()
        }

    @Before
    fun setup() {
        // Setup before each test
    }

    @Test
    fun testGetPokemonListSuccess() =
        runTest {
            // Arrange
            val mockResponse =
                """
                {
                    "count": 1292,
                    "next": "https://pokeapi.co/api/v2/pokemon?offset=20&limit=20",
                    "previous": null,
                    "results": [
                        {
                            "name": "bulbasaur",
                            "url": "https://pokeapi.co/api/v2/pokemon/1/"
                        },
                        {
                            "name": "ivysaur",
                            "url": "https://pokeapi.co/api/v2/pokemon/2/"
                        }
                    ]
                }
                """.trimIndent()

            mockHttpClient = createMockHttpClient(mockResponse)
            pokemonApi = createPokemonApiWithMockClient(mockHttpClient)

            // Act
            val result = pokemonApi.getPokemonList(offset = 0, limit = 20)

            // Assert
            assertNotNull(result)
            assertEquals(1292, result.count)
            assertEquals(2, result.results.size)
            assertEquals("bulbasaur", result.results[0].name)
            assertEquals("ivysaur", result.results[1].name)
        }

    @Test
    fun testGetPokemonSuccess() =
        runTest {
            // Arrange
            val mockResponse =
                """
                {
                    "id": 1,
                    "name": "bulbasaur",
                    "height": 7,
                    "weight": 69,
                    "base_experience": 64,
                    "types": [
                        {
                            "slot": 1,
                            "type": {
                                "name": "grass",
                                "url": "https://pokeapi.co/api/v2/type/12/"
                            }
                        },
                        {
                            "slot": 2,
                            "type": {
                                "name": "poison",
                                "url": "https://pokeapi.co/api/v2/type/4/"
                            }
                        }
                    ],
                    "abilities": [
                        {
                            "slot": 1,
                            "is_hidden": false,
                            "ability": {
                                "name": "overgrow",
                                "url": "https://pokeapi.co/api/v2/ability/65/"
                            }
                        }
                    ],
                    "stats": [
                        {
                            "stat": {
                                "name": "hp",
                                "url": "https://pokeapi.co/api/v2/stat/1/"
                            },
                            "effort": 0,
                            "base_stat": 45
                        }
                    ],
                    "sprites": {
                        "back_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/1.png",
                        "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                        "other": {
                            "official-artwork": {
                                "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                                "front_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/shiny/1.png"
                            }
                        }
                    }
                }
                """.trimIndent()

            mockHttpClient = createMockHttpClient(mockResponse)
            pokemonApi = createPokemonApiWithMockClient(mockHttpClient)

            // Act
            val result = pokemonApi.getPokemon("bulbasaur")

            // Assert
            assertNotNull(result)
            assertEquals(1, result.id)
            assertEquals("bulbasaur", result.name)
            assertEquals(7, result.height)
            assertEquals(69, result.weight)
            assertEquals(64, result.baseExperience)
            assertEquals(2, result.types.size)
            assertEquals(1, result.abilities.size)
            assertEquals(1, result.stats.size)
        }

    @Test
    fun testGetAbilitySuccess() =
        runTest {
            // Arrange
            val mockResponse =
                """
                {
                    "id": 65,
                    "name": "overgrow"
                }
                """.trimIndent()

            mockHttpClient = createMockHttpClient(mockResponse)
            pokemonApi = createPokemonApiWithMockClient(mockHttpClient)

            // Act
            val result = pokemonApi.getAbility("overgrow")

            // Assert
            assertNotNull(result)
            assertEquals(65, result.id)
            assertEquals("overgrow", result.name)
        }

    @Test
    fun testGetStatSuccess() =
        runTest {
            // Arrange
            val mockResponse =
                """
                {
                    "id": 1,
                    "name": "hp"
                }
                """.trimIndent()

            mockHttpClient = createMockHttpClient(mockResponse)
            pokemonApi = createPokemonApiWithMockClient(mockHttpClient)

            // Act
            val result = pokemonApi.getStat("hp")

            // Assert
            assertNotNull(result)
            assertEquals(1, result.id)
            assertEquals("hp", result.name)
        }

    @Test
    fun testGetTypeSuccess() =
        runTest {
            // Arrange
            val mockResponse =
                """
                {
                    "id": 12,
                    "name": "grass"
                }
                """.trimIndent()

            mockHttpClient = createMockHttpClient(mockResponse)
            pokemonApi = createPokemonApiWithMockClient(mockHttpClient)

            // Act
            val result = pokemonApi.getType("grass")

            // Assert
            assertNotNull(result)
            assertEquals(12, result.id)
            assertEquals("grass", result.name)
        }

    @Test
    fun testGetPokemonListWithCustomParameters() =
        runTest {
            // Arrange
            val mockResponse =
                """
                {
                    "count": 1292,
                    "next": "https://pokeapi.co/api/v2/pokemon?offset=40&limit=20",
                    "previous": "https://pokeapi.co/api/v2/pokemon?offset=0&limit=20",
                    "results": [
                        {
                            "name": "charmander",
                            "url": "https://pokeapi.co/api/v2/pokemon/4/"
                        }
                    ]
                }
                """.trimIndent()

            mockHttpClient = createMockHttpClient(mockResponse)
            pokemonApi = createPokemonApiWithMockClient(mockHttpClient)

            // Act
            val result = pokemonApi.getPokemonList(offset = 20, limit = 20)

            // Assert
            assertNotNull(result)
            assertEquals(1, result.results.size)
            assertEquals("charmander", result.results[0].name)
        }

    @Test
    fun testGetPokemonListWithEmptyResults() =
        runTest {
            // Arrange
            val mockResponse =
                """
                {
                    "count": 0,
                    "next": null,
                    "previous": null,
                    "results": []
                }
                """.trimIndent()

            mockHttpClient = createMockHttpClient(mockResponse)
            pokemonApi = createPokemonApiWithMockClient(mockHttpClient)

            // Act
            val result = pokemonApi.getPokemonList(offset = 0, limit = 20)

            // Assert
            assertNotNull(result)
            assertEquals(0, result.count)
            assertEquals(0, result.results.size)
        }

    @Test
    fun testGetPokemonWithMultipleTypes() =
        runTest {
            // Arrange
            val mockResponse =
                """
                {
                    "id": 4,
                    "name": "charmander",
                    "height": 6,
                    "weight": 85,
                    "base_experience": 62,
                    "types": [
                        {
                            "slot": 1,
                            "type": {
                                "name": "fire",
                                "url": "https://pokeapi.co/api/v2/type/10/"
                            }
                        }
                    ],
                    "abilities": [
                        {
                            "slot": 1,
                            "is_hidden": false,
                            "ability": {
                                "name": "blaze",
                                "url": "https://pokeapi.co/api/v2/ability/66/"
                            }
                        },
                        {
                            "slot": 3,
                            "is_hidden": true,
                            "ability": {
                                "name": "solar-power",
                                "url": "https://pokeapi.co/api/v2/ability/94/"
                            }
                        }
                    ],
                    "stats": [
                        {
                            "stat": {
                                "name": "hp",
                                "url": "https://pokeapi.co/api/v2/stat/1/"
                            },
                            "effort": 0,
                            "base_stat": 39
                        },
                        {
                            "stat": {
                                "name": "attack",
                                "url": "https://pokeapi.co/api/v2/stat/2/"
                            },
                            "effort": 0,
                            "base_stat": 52
                        }
                    ],
                    "sprites": {
                        "back_default": "https://example.com/back.png",
                        "front_default": "https://example.com/front.png",
                        "other": {
                            "official-artwork": {
                                "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                                "front_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/shiny/1.png"
                            }
                        }
                    }
                }
                """.trimIndent()

            mockHttpClient = createMockHttpClient(mockResponse)
            pokemonApi = createPokemonApiWithMockClient(mockHttpClient)

            // Act
            val result = pokemonApi.getPokemon("charmander")

            // Assert
            assertNotNull(result)
            assertEquals(4, result.id)
            assertEquals("charmander", result.name)
            assertEquals(1, result.types.size)
            assertEquals(2, result.abilities.size)
            assertEquals(2, result.stats.size)
        }

    @Test
    fun testGetPokemonListPagination() =
        runTest {
            // Arrange
            val mockResponse =
                """
                {
                    "count": 1292,
                    "next": "https://pokeapi.co/api/v2/pokemon?offset=40&limit=20",
                    "previous": "https://pokeapi.co/api/v2/pokemon?offset=0&limit=20",
                    "results": [
                        {
                            "name": "pokemon21",
                            "url": "https://pokeapi.co/api/v2/pokemon/21/"
                        },
                        {
                            "name": "pokemon22",
                            "url": "https://pokeapi.co/api/v2/pokemon/22/"
                        }
                    ]
                }
                """.trimIndent()

            mockHttpClient = createMockHttpClient(mockResponse)
            pokemonApi = createPokemonApiWithMockClient(mockHttpClient)

            // Act
            val result = pokemonApi.getPokemonList(offset = 20, limit = 20)

            // Assert
            assertNotNull(result)
            assertEquals(1292, result.count)
            assertEquals(2, result.results.size)
            assertNotNull(result.next)
            assertNotNull(result.previous)
        }
}
