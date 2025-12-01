package com.example.hpoke.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaginatedPokemonResponse(
    @SerialName(value = "count") val count: Int,
    @SerialName(value = "next") val next: String?,
    @SerialName(value = "previous") val previous: String?,
    @SerialName(value = "results") val results: List<PokemonResponse>,
)

