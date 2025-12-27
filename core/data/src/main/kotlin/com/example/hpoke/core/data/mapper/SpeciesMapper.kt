package com.example.hpoke.core.data.mapper

import com.example.hpoke.core.database.model.SpritesEntity
import com.example.hpoke.core.model.Species

fun SpritesEntity.asModel() = Species(
    id = id,
    frontDefault = frontDefault,
    frontShiny = frontShiny,
)