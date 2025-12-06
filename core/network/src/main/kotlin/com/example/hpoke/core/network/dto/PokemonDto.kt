package com.example.hpoke.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDto(
    @SerialName(value = "id") val id: Int,
    @SerialName(value = "name") val name: String,
    @SerialName(value = "height") val height: Int,
    @SerialName(value = "weight") val weight: Int,
    @SerialName(value = "base_experience") val baseExperience: Int? = null,
    @SerialName(value = "types") val types: List<PokemonTypeDto>,
    @SerialName(value = "abilities") val abilities: List<PokemonAbilityDto>,
    @SerialName(value = "stats") val stats: List<PokemonStatDto>,
    @SerialName(value = "sprites") val sprites: SpritesDto
)