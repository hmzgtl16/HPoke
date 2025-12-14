package com.example.hpoke.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpritesDto(
    @SerialName(value = "other") val other: OtherSpritesDto,
)

