package com.example.hpoke.core.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class PokemonWithAbility(
    @Embedded val pokemonAbilityCrossRef: PokemonAbilityCrossRef,
    @Relation(
        parentColumn = "ability_id",
        entityColumn = "id"
    )
    val ability: AbilityEntity
)
