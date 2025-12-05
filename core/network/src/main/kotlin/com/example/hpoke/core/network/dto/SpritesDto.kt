package com.example.hpoke.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpritesDto(
    @SerialName(value = "front_default") val frontDefault: String? = null,
    @SerialName(value = "back_default") val backDefault: String? = null,
    @SerialName(value = "front_shiny") val frontShiny: String? = null,
    @SerialName(value = "back_shiny") val backShiny: String? = null
    // there are more fields, you can add when needed
)