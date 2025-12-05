package com.example.hpoke.core.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class PokemonListItemDto(
    val name: String,
    val url: String
)

