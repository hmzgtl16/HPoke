package com.example.hpoke.core.data.mapper

import com.example.hpoke.core.database.model.PokemonEntity
import com.example.hpoke.core.database.model.PokemonFull
import com.example.hpoke.core.model.Pokemon
import com.example.hpoke.core.network.dto.PokemonDto

fun PokemonFull.asModel() = Pokemon(
    id = pokemon.id,
    name = pokemon.name,
    height = pokemon.height,
    weight = pokemon.weight,
    baseExperience = pokemon.baseExperience,
    species = species.asModel(),
    stats = stats.asModel(),
    types = types.asModel(),
    abilities = abilities.asModel()
)

fun List<PokemonFull>.asModel() = map(PokemonFull::asModel)

fun PokemonDto.toEntity() = PokemonEntity(
    id = id,
    name = name,
    height = height,
    weight = weight,
    baseExperience = baseExperience,
    spritesId = id
)