package com.example.hpoke.core.model

data class Ability(
    val id: Int,
    val name: String,
    val isHidden: Boolean,
    val slot: Int
)