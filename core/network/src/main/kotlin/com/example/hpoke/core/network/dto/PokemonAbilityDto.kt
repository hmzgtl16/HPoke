package com.example.hpoke.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonAbilityDto(
    @SerialName(value = "is_hidden") val isHidden: Boolean,
    @SerialName(value = "slot") val slot: Int,
    @SerialName(value = "ability") val ability: NamedApiResourceDto
)
