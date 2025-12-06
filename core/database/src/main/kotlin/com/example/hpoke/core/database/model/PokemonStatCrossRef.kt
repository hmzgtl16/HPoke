package com.example.hpoke.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "pokemon_stat_cross_ref",
    primaryKeys = ["pokemon_id", "stat_id"],
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
            childColumns = ["stat_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["stat_id"])]
)
data class PokemonStatCrossRef(
    @ColumnInfo(name = "pokemon_id") val pokemonId: Int,
    @ColumnInfo(name = "stat_id") val statId: Int,
    @ColumnInfo(name = "base_stat") val baseStat: Int,
    @ColumnInfo(name = "effort") val effort: Int
)