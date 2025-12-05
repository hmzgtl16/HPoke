package com.example.hpoke.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AbilityDto(
    @SerialName(value = "id") val id: Int,
    @SerialName(value = "name") val name: String,
)
