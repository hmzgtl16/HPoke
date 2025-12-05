package com.example.hpoke.core.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "pokemon_ability_cross_ref",
    primaryKeys = ["pokemonId", "abilityId"],
    foreignKeys = [
        ForeignKey(
            entity = PokemonEntity::class,
            parentColumns = ["id"],
            childColumns = ["pokemonId"],
            onDelete = ForeignKey.Companion.CASCADE
        ),
        ForeignKey(
            entity = AbilityEntity::class,
            parentColumns = ["id"],
            childColumns = ["abilityId"],
            onDelete = ForeignKey.Companion.CASCADE
        )
    ],
    indices = [Index(value = ["abilityId"])]
)
data class PokemonAbilityCrossRef(
    val pokemonId: Int,
    val abilityId: Int,
    val isHidden: Boolean,
    val slot: Int
)