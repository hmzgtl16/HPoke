package com.example.hpoke.core.model

data class Pokemon(
    val id: Int,
    val name: String,
    val height: Int?,
    val weight: Int?,
    val baseExperience: Int?,
    val species: Species,
    val types: List<Type>,
    val stats: List<Stat>,
    val abilities: List<Ability>,
)

