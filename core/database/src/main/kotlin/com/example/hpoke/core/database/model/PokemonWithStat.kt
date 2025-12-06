package com.example.hpoke.core.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class PokemonWithStat(
    @Embedded val pokemonStatCrossRef: PokemonStatCrossRef,
    @Relation(
        parentColumn = "stat_id",
        entityColumn = "id"
    )
    val stat: StatEntity
)