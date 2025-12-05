package com.example.hpoke.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("game_index") val gameIndex: Int? = null,
    @SerialName("is_battle_only") val isBattleOnly: Boolean? = null
)
