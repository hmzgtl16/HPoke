package com.example.hpoke.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OfficialArtwork(
    @SerialName(value = "front_default") val frontDefault: String?,
    @SerialName(value = "front_shiny") val frontShiny: String?,
)