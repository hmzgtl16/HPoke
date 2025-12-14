package com.example.hpoke.core.data.mapper

import com.example.hpoke.core.database.model.SpritesEntity
import com.example.hpoke.core.model.Species
import com.example.hpoke.core.network.dto.SpritesDto

fun SpritesEntity.asModel() = Species(
    id = id,
    frontDefault = frontDefault,
    frontShiny = frontShiny,
)

fun SpritesDto.asEntity(id: Int) = SpritesEntity(
    id = id,
    frontDefault = other.officialArtwork.frontDefault,
    frontShiny = other.officialArtwork.frontShiny
)