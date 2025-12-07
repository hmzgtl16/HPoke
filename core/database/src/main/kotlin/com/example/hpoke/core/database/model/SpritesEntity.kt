package com.example.hpoke.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "sprites",
)
data class SpritesEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "front_default") val frontDefault: String? = null,
    @ColumnInfo(name = "front_shiny") val frontShiny: String? = null
)


