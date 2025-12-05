package com.example.hpoke.core.model

data class Species(
    val id: Int,
    val frontDefault: String? = null,
    val backDefault: String? = null,
    val frontShiny: String? = null,
    val backShiny: String? = null
)
