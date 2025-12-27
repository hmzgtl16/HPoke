package com.example.hpoke.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "pokemon_type_cross_ref",
    primaryKeys = ["pokemon_id", "slot"],
    foreignKeys = [
        ForeignKey(
            entity = PokemonEntity::class,
            parentColumns = ["id"],
            childColumns = ["pokemon_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = TypeEntity::class,
            parentColumns = ["id"],
            childColumns = ["type_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["pokemon_id"]),
        Index(value = ["type_id"])
    ]
)
data class PokemonTypeCrossRef(
    @ColumnInfo(name = "pokemon_id") val pokemonId: Int,
    @ColumnInfo(name = "type_id") val typeId: Int,
    @ColumnInfo(name = "slot") val slot: Int // 1 = primary, 2 = secondary
)

