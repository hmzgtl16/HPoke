package com.example.hpoke.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "species",
    indices = [Index(value = ["name"], unique = true)]
)
data class SpeciesEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "front_default") val frontDefault: String? = null,
    @ColumnInfo(name = "back_default") val backDefault: String? = null,
    @ColumnInfo(name = "front_shiny") val frontShiny: String? = null,
    @ColumnInfo(name = "back_shiny") val backShiny: String? = null
)


