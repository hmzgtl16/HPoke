package com.example.hpoke.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonTypeDto(
    @SerialName(value = "slot") val slot: Int,
    @SerialName(value = "type") val type: NamedApiResourceDto
)