package com.example.hpoke.core.database.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "ability",
    indices = [Index(value = ["name"], unique = true)]
)
data class AbilityEntity(
    @PrimaryKey val id: Int,
    val name: String,
)