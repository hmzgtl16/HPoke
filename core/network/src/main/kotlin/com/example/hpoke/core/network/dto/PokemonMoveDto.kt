package com.example.hpoke.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonMoveDto(
    @SerialName(value = "move") val move: NamedApiResourceDto
    // you can add version_group_details if you need them later
)