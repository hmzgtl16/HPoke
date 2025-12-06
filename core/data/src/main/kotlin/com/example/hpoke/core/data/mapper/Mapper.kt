package com.example.hpoke.core.data.mapper

import com.example.hpoke.core.database.model.MoveEntity
import com.example.hpoke.core.database.model.PokemonFull
import com.example.hpoke.core.database.model.PokemonWithAbility
import com.example.hpoke.core.database.model.PokemonWithStat
import com.example.hpoke.core.database.model.PokemonWithType
import com.example.hpoke.core.database.model.SpeciesEntity
import com.example.hpoke.core.model.Ability
import com.example.hpoke.core.model.Move
import com.example.hpoke.core.model.Pokemon
import com.example.hpoke.core.model.Species
import com.example.hpoke.core.model.Stat
import com.example.hpoke.core.model.Type

fun PokemonFull.asModel() = Pokemon(
    id = pokemon.id,
    name = pokemon.name,
    height = pokemon.height,
    weight = pokemon.weight,
    baseExperience = pokemon.baseExperience,
    species = species.asModel(),
    stats = stats.asModel(),
    types = types.asModel(),
    moves = moves.asModel(),
    abilities = abilities.asModel()
)

fun SpeciesEntity.asModel() = Species(
    id = id,
    frontDefault = frontDefault,
    backDefault = backDefault,
    frontShiny = frontShiny,
    backShiny = backShiny
)

fun MoveEntity.asModel() = Move(
    id = id,
    name = name,
    accuracy = accuracy,
    power = power,
    pp = pp
)

fun PokemonWithStat.asModel() = Stat(
    id = stat.id,
    name = stat.name,
    gameIndex = stat.gameIndex,
    isBattleOnly = stat.isBattleOnly,
    baseStat = pokemonStatCrossRef.baseStat,
    effort = pokemonStatCrossRef.effort
)

fun PokemonWithType.asModel() = Type(
    id = type.id,
    name = type.name,
    slot = pokemonTypeCrossRef.slot
)

fun PokemonWithAbility.asModel() = Ability(
    id = ability.id,
    name = ability.name,
    isHidden = pokemonAbilityCrossRef.isHidden,
    slot = pokemonAbilityCrossRef.slot
)

fun List<PokemonFull>.asModel() = map(PokemonFull::asModel)

fun List<MoveEntity>.asModel() = map(MoveEntity::asModel)

fun List<PokemonWithStat>.asModel() = map(PokemonWithStat::asModel)

fun List<PokemonWithType>.asModel() = map(PokemonWithType::asModel)

fun List<PokemonWithAbility>.asModel() = map(PokemonWithAbility::asModel)