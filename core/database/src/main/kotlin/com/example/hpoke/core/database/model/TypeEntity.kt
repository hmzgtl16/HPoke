package com.example.hpoke.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "type",
    indices = [Index(value = ["name"], unique = true)]
)
data class TypeEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String
)