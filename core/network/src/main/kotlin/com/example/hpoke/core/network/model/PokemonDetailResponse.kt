package com.example.hpoke.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailResponse(
    @SerialName(value = "id") val id: Int,
    @SerialName(value = "name") val name: String,
    @SerialName(value = "base_experience") val experience: Int,
    @SerialName(value = "height") val height: Int,
    @SerialName(value = "is_default") val isDefault: Boolean,
    @SerialName(value = "order") val order: Int,
    @SerialName(value = "weight") val weight: Int,

    @SerialName(value = "types") val types: List<TypeResponse>,
    @SerialName(value = "stats") val stats: List<StatsResponse>,
)