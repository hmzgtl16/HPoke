package com.example.hpoke.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hpoke.core.database.model.TypeEntity

@Dao
interface TypeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTypes(types: List<TypeEntity>): List<Long>

    @Query(value = "SELECT * FROM type")
    suspend fun getAllTypes(): List<TypeEntity>

    @Query(value = "SELECT * FROM type WHERE name = :name")
    suspend fun getTypeByName(name: String): TypeEntity?
}