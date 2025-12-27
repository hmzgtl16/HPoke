package com.example.hpoke.core.network.dto

import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class PokemonDtoSerializationTest {

    private lateinit var json: Json

    @Before
    fun setup() {
        json = Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        }
    }

    @Test
    fun testDeserializePokemonDto() {
        // Arrange
        val jsonString = """
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
                    }
                ],
                "abilities": [
                    {
                        "ability": {
                            "name": "overgrow",
                            "url": "https://pokeapi.co/api/v2/ability/65/"
                        },
                        "is_hidden": false,
                        "slot": 1
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
                    "other": {
                        "official-artwork": {
                            "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                            "front_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/shiny/1.png"
                        }
                    }
                }
            }
        """.trimIndent()

        // Act
        val result = json.decodeFromString<PokemonDto>(jsonString)

        // Assert
        assertNotNull(result)
        assertEquals(1, result.id)
        assertEquals("bulbasaur", result.name)
        assertEquals(7, result.height)
        assertEquals(69, result.weight)
        assertEquals(64, result.baseExperience)
        assertEquals(1, result.types.size)
        assertEquals(1, result.abilities.size)
        assertEquals(1, result.stats.size)
    }

    @Test
    fun testDeserializePokemonDtoWithoutOptionalFields() {
        // Arrange
        val jsonString = """
            {
                "id": 2,
                "name": "ivysaur",
                "height": 10,
                "weight": 130,
                "types": [],
                "abilities": [],
                "stats": [],
                "sprites": {
                    "back_default": null,
                    "front_default": null,
                    "other": {
                        "official-artwork": {
                            "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/2.png",
                            "front_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/shiny/2.png"
                        }
                    }
                }
            }
        """.trimIndent()

        // Act
        val result = json.decodeFromString<PokemonDto>(jsonString)

        // Assert
        assertNotNull(result)
        assertEquals(2, result.id)
        assertEquals("ivysaur", result.name)
        assertEquals(null, result.baseExperience)
        assertEquals(0, result.types.size)
    }

    @Test
    fun testDeserializePokemonListDto() {
        // Arrange
        val jsonString = """
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

        // Act
        val result = json.decodeFromString<NamedApiResourceListDto>(jsonString)

        // Assert
        assertNotNull(result)
        assertEquals(1292, result.count)
        assertEquals(2, result.results.size)
        assertEquals("bulbasaur", result.results[0].name)
        assertEquals("https://pokeapi.co/api/v2/pokemon/1/", result.results[0].url)
    }

    @Test
    fun testDeserializeAbilityDto() {
        // Arrange
        val jsonString = """
            {
                "id": 65,
                "name": "overgrow"
            }
        """.trimIndent()

        // Act
        val result = json.decodeFromString<AbilityDto>(jsonString)

        // Assert
        assertNotNull(result)
        assertEquals(65, result.id)
        assertEquals("overgrow", result.name)
    }

    @Test
    fun testDeserializeStatDto() {
        // Arrange
        val jsonString = """
            {
                "id": 1,
                "name": "hp"
            }
        """.trimIndent()

        // Act
        val result = json.decodeFromString<StatDto>(jsonString)

        // Assert
        assertNotNull(result)
        assertEquals(1, result.id)
        assertEquals("hp", result.name)
    }

    @Test
    fun testDeserializeTypeDto() {
        // Arrange
        val jsonString = """
            {
                "id": 12,
                "name": "grass"
            }
        """.trimIndent()

        // Act
        val result = json.decodeFromString<TypeDto>(jsonString)

        // Assert
        assertNotNull(result)
        assertEquals(12, result.id)
        assertEquals("grass", result.name)
    }

    @Test
    fun testDeserializeNamedApiResourceDto() {
        // Arrange
        val jsonString = """
            {
                "name": "bulbasaur",
                "url": "https://pokeapi.co/api/v2/pokemon/1/"
            }
        """.trimIndent()

        // Act
        val result = json.decodeFromString<NamedApiResourceDto>(jsonString)

        // Assert
        assertNotNull(result)
        assertEquals("bulbasaur", result.name)
        assertEquals("https://pokeapi.co/api/v2/pokemon/1/", result.url)
    }

    @Test
    fun testDeserializePokemonTypeDto() {
        // Arrange
        val jsonString = """
            {
                "slot": 1,
                "type": {
                    "name": "grass",
                    "url": "https://pokeapi.co/api/v2/type/12/"
                }
            }
        """.trimIndent()

        // Act
        val result = json.decodeFromString<PokemonTypeDto>(jsonString)

        // Assert
        assertNotNull(result)
        assertEquals(1, result.slot)
        assertEquals("grass", result.type.name)
    }

    @Test
    fun testDeserializePokemonStatDto() {
        // Arrange
        val jsonString = """
            {
                "stat": {
                    "name": "hp",
                    "url": "https://pokeapi.co/api/v2/stat/1/"
                },
                "effort": 0,
                "base_stat": 45
            }
        """.trimIndent()

        // Act
        val result = json.decodeFromString<PokemonStatDto>(jsonString)

        // Assert
        assertNotNull(result)
        assertEquals("hp", result.stat.name)
        assertEquals(0, result.effort)
        assertEquals(45, result.baseStat)
    }

    @Test
    fun testDeserializePokemonAbilityDto() {
        // Arrange
        val jsonString = """
            {
                "slot": 1,
                "is_hidden": false,
                "ability": {
                    "name": "overgrow",
                    "url": "https://pokeapi.co/api/v2/ability/65/"
                }
            }
        """.trimIndent()

        // Act
        val result = json.decodeFromString<PokemonAbilityDto>(jsonString)

        // Assert
        assertNotNull(result)
        assertEquals(1, result.slot)
        assertEquals(false, result.isHidden)
        assertEquals("overgrow", result.ability.name)
    }
}

