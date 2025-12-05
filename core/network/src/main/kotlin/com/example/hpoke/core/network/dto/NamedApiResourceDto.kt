package com.example.hpoke.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NamedApiResourceDto(
    @SerialName(value = "name") val name: String,
    @SerialName(value = "url") val url: String
)