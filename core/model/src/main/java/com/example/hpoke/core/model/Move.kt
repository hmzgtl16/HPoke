package com.example.hpoke.core.model

data class Move(
    val id: Int,
    val name: String,
    val accuracy: Int?,
    val power: Int?,
    val pp: Int?,
)