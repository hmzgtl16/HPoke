package com.example.hpoke.core.data.mapper

import androidx.paging.PagingData
import androidx.paging.map
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

fun PokemonDto.toEntity() = PokemonEntity(
    id = id,
    name = name,
    height = height,
    weight = weight,
    baseExperience = baseExperience,
    spritesId = id
)

fun PagingData<PokemonFull>.asModel(): PagingData<Pokemon> = map(PokemonFull::asModel)