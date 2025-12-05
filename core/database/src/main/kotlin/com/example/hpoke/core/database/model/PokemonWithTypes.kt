package com.example.hpoke.core.database.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class PokemonWithTypes(
    @Embedded val pokemon: PokemonEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = PokemonTypeCrossRef::class,
            parentColumn = "pokemonId",
            entityColumn = "typeId"
        )
    )
    val types: List<TypeEntity>
)