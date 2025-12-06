package com.example.hpoke.core.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class PokemonWithType(
    @Embedded val pokemonTypeCrossRef: PokemonTypeCrossRef,
    @Relation(
        parentColumn = "type_id",
        entityColumn = "id"
    )
    val type: TypeEntity
)
