package com.example.hpoke.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OtherSpritesDto(
    @SerialName(value = "official-artwork") val officialArtwork: OfficialArtworkDto
)