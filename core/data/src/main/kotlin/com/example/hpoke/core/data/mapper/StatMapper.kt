package com.example.hpoke.core.data.mapper

import com.example.hpoke.core.database.model.PokemonWithStat
import com.example.hpoke.core.database.model.StatEntity
import com.example.hpoke.core.model.Stat
import com.example.hpoke.core.network.dto.StatDto

fun PokemonWithStat.asModel() = Stat(
    id = stat.id,
    name = stat.name,
    gameIndex = stat.gameIndex,
    isBattleOnly = stat.isBattleOnly,
    baseStat = pokemonStatCrossRef.baseStat,
    effort = pokemonStatCrossRef.effort
)

fun StatDto.asEntity() = StatEntity(
    id = id,
    name = name,
    gameIndex = gameIndex,
    isBattleOnly = isBattleOnly
)
