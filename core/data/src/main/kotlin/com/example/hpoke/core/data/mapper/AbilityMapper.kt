package com.example.hpoke.core.data.mapper

import com.example.hpoke.core.database.model.AbilityEntity
import com.example.hpoke.core.database.model.PokemonWithAbility
import com.example.hpoke.core.model.Ability
import com.example.hpoke.core.network.dto.AbilityDto

fun PokemonWithAbility.asModel() = Ability(
    id = ability.id,
    name = ability.name,
    isHidden = pokemonAbilityCrossRef.isHidden,
    slot = pokemonAbilityCrossRef.slot
)

fun List<PokemonWithAbility>.asModel() = map(PokemonWithAbility::asModel)

fun AbilityDto.asEntity() = AbilityEntity(
    id = id,
    name = name
)