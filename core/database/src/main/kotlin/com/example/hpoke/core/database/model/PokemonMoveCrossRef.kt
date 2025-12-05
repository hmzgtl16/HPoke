package com.example.hpoke.core.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "pokemon_move_cross_ref",
    primaryKeys = ["pokemonId", "moveId"],
    foreignKeys = [
        ForeignKey(
            entity = PokemonEntity::class,
            parentColumns = ["id"],
            childColumns = ["pokemonId"],
            onDelete = ForeignKey.Companion.CASCADE
        ),
        ForeignKey(
            entity = MoveEntity::class,
            parentColumns = ["id"],
            childColumns = ["moveId"],
            onDelete = ForeignKey.Companion.CASCADE
        )
    ],
    indices = [Index(value = ["moveId"])]
)
data class PokemonMoveCrossRef(
    val pokemonId: Int,
    val moveId: Int
    // you can add level, learnMethod, etc. later
)