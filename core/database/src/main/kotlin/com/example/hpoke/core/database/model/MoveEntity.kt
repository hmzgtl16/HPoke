package com.example.hpoke.core.database.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "move",
    indices = [Index(value = ["name"], unique = true)]
)
data class MoveEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val accuracy: Int?,
    val power: Int?,
    val pp: Int?,
)