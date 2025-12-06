package com.example.hpoke.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "pokemon_move_cross_ref",
    primaryKeys = ["pokemon_id", "move_id"],
    foreignKeys = [
        ForeignKey(
            entity = PokemonEntity::class,
            parentColumns = ["id"],
            childColumns = ["pokemon_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = MoveEntity::class,
            parentColumns = ["id"],
            childColumns = ["move_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["move_id"])]
)
data class PokemonMoveCrossRef(
    @ColumnInfo(name = "pokemon_id") val pokemon_id: Int,
    @ColumnInfo(name = "move_id") val move_id: Int
)