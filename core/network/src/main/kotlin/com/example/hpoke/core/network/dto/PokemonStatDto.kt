package com.example.hpoke.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonStatDto(
    @SerialName(value = "base_stat") val baseStat: Int,
    @SerialName(value = "effort") val effort: Int,
    @SerialName(value = "stat") val stat: NamedApiResourceDto
)