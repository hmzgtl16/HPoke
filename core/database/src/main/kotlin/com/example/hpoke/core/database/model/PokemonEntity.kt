package com.example.hpoke.core.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "pokemon",
    indices = [Index(value = ["name"], unique = true)],
    foreignKeys = [
        ForeignKey(
            entity = SpritesEntity::class,
            parentColumns = ["id"],
            childColumns = ["speciesId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PokemonEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val height: Int?,
    val weight: Int?,
    val baseExperience: Int?,
    val spritesId: Int?
)