package com.example.hpoke.core.data.mapper

import com.example.hpoke.core.database.model.PokemonWithType
import com.example.hpoke.core.database.model.TypeEntity
import com.example.hpoke.core.model.Type
import com.example.hpoke.core.network.dto.TypeDto


fun PokemonWithType.asModel() = Type(
    id = type.id,
    name = type.name,
    slot = pokemonTypeCrossRef.slot
)

fun List<PokemonWithType>.asModel() = map(PokemonWithType::asModel)

fun TypeDto.asEntity() = TypeEntity(
    id = id,
    name = name
)