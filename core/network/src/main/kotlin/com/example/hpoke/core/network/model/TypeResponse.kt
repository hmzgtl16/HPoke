package com.example.hpoke.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TypeResponse(
    @SerialName(value = "slot") val slot: Int,
    @SerialName(value = "type") val type: Type,
)