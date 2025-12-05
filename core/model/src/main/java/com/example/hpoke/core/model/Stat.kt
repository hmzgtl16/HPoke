package com.example.hpoke.core.model

data class Stat(
    val id: Int,
    val name: String,
    val gameIndex: Int? = null,
    val isBattleOnly: Boolean? = null,
    val baseStat: Int,
    val effort: Int
)