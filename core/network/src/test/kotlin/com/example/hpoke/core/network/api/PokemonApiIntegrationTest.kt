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

class PokemonApiIntegrationTest {

    private lateinit var pokemonApi: PokemonApi

    private fun createMockHttpClient(responseBody: String): HttpClient {
        val mockEngine = MockEngine { _ ->
            respond(
                content = ByteReadChannel(responseBody),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        return HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }

    private fun createPokemonApiWithMockClient(client: HttpClient): PokemonApi {
        return object : PokemonApi {
            override suspend fun getPokemonList(offset: Int, limit: Int) =
                client.get("pokemon") {
                    parameter("offset", offset)
                    parameter("limit", limit)
                }.body<NamedApiResourceListDto>()

            override suspend fun getPokemon(name: String) =
                client.get("pokemon/$name").body<PokemonDto>()

            override suspend fun getAbility(name: String) =
                client.get("ability/$name").body<AbilityDto>()

            override suspend fun getStat(name: String) =
                client.get("stat/$name").body<StatDto>()

            override suspend fun getType(name: String) =
                client.get("type/$name").body<TypeDto>()
        }
    }

    @Before
    fun setup() {
        // No-op setup
    }

    @Test
    fun testGetPokemonListWithEmptyResults() = runTest {
        // Arrange
        val mockResponse = """
            {
                "count": 0,
                "next": null,
                "previous": null,
                "results": []
            }
        """.trimIndent()

        val mockHttpClient = createMockHttpClient(mockResponse)
        pokemonApi = createPokemonApiWithMockClient(mockHttpClient)

        // Act
        val result = pokemonApi.getPokemonList(offset = 0, limit = 20)

        // Assert
        assertNotNull(result)
        assertEquals(0, result.count)
        assertEquals(0, result.results.size)
    }

    @Test
    fun testGetPokemonWithMultipleTypes() = runTest {
        // Arrange
        val mockResponse = """
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
                            "front_default": "https://example.com/official.png",
                            "front_shiny": "https://example.com/official.png"
                        }
                    }
                }
            }
        """.trimIndent()

        val mockHttpClient = createMockHttpClient(mockResponse)
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
    fun testGetPokemonListPagination() = runTest {
        // Arrange - simulating page 2
        val mockResponse = """
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

        val mockHttpClient = createMockHttpClient(mockResponse)
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

    @Test
    fun testGetAbilityWithSpecialCharacters() = runTest {
        // Arrange
        val mockResponse = """
            {
                "id": 1,
                "name": "stench"
            }
        """.trimIndent()

        val mockHttpClient = createMockHttpClient(mockResponse)
        pokemonApi = createPokemonApiWithMockClient(mockHttpClient)

        // Act
        val result = pokemonApi.getAbility("stench")

        // Assert
        assertNotNull(result)
        assertEquals("stench", result.name)
    }

    @Test
    fun testGetStatByName() = runTest {
        // Arrange
        val mockResponse = """
            {
                "id": 2,
                "name": "attack"
            }
        """.trimIndent()

        val mockHttpClient = createMockHttpClient(mockResponse)
        pokemonApi = createPokemonApiWithMockClient(mockHttpClient)

        // Act
        val result = pokemonApi.getStat("attack")

        // Assert
        assertNotNull(result)
        assertEquals(2, result.id)
        assertEquals("attack", result.name)
    }

    @Test
    fun testGetTypeByName() = runTest {
        // Arrange
        val mockResponse = """
            {
                "id": 10,
                "name": "fire"
            }
        """.trimIndent()

        val mockHttpClient = createMockHttpClient(mockResponse)
        pokemonApi = createPokemonApiWithMockClient(mockHttpClient)

        // Act
        val result = pokemonApi.getType("fire")

        // Assert
        assertNotNull(result)
        assertEquals(10, result.id)
        assertEquals("fire", result.name)
    }
}

