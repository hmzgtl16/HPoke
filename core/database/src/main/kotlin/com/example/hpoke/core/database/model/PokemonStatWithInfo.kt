package com.example.hpoke.core.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class PokemonStatWithInfo(
    @Embedded val pokemonStat: PokemonStatEntity,
    @Relation(
        parentColumn = "statId",
        entityColumn = "id"
    )
    val stat: StatEntity
)