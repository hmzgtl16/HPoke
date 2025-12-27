package com.example.hpoke.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "pokemon_ability_cross_ref",
    primaryKeys = ["pokemon_id", "ability_id"],
    foreignKeys = [
        ForeignKey(
            entity = PokemonEntity::class,
            parentColumns = ["id"],
            childColumns = ["pokemon_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AbilityEntity::class,
            parentColumns = ["id"],
            childColumns = ["ability_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["pokemon_id"]),
        Index(value = ["ability_id"])
    ]
)
data class PokemonAbilityCrossRef(
    @ColumnInfo(name = "pokemon_id") val pokemonId: Int,
    @ColumnInfo(name = "ability_id") val abilityId: Int,
    @ColumnInfo(name = "is_hidden") val isHidden: Boolean,
    @ColumnInfo(name = "slot") val slot: Int
)