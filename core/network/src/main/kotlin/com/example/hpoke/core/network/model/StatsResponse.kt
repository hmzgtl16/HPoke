package com.example.hpoke.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatsResponse(
    @SerialName(value = "base_stat") val baseStat: Int,
    @SerialName(value = "effort") val effort: Int,
    @SerialName(value = "stat") val stat: Stat,
)