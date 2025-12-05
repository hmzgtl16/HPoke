package com.example.hpoke.core.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class PokemonWithStats(
    @Embedded val pokemon: PokemonEntity,
    @Relation(
        entity = PokemonStatEntity::class,
        parentColumn = "id",
        entityColumn = "pokemonId"
    )
    val stats: List<PokemonStatWithInfo>
)