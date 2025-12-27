package com.example.hpoke.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "stat",
    indices = [Index(value = ["name"], unique = true)]
)
data class StatEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String
)