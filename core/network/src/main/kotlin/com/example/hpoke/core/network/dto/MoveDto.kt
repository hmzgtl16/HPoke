package com.example.hpoke.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoveDto(
    @SerialName(value = "id") val id: Int,
    @SerialName(value = "name") val name: String,
    @SerialName(value = "accuracy") val accuracy: Int? = null,
    @SerialName(value = "power") val power: Int? = null,
    @SerialName(value = "pp") val pp: Int? = null,
)