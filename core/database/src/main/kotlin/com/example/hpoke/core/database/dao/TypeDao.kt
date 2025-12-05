package com.example.hpoke.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hpoke.core.database.model.TypeEntity

@Dao
interface TypeDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertType(type: TypeEntity)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertTypes(types: List<TypeEntity>)

    @Query("SELECT * FROM type WHERE id = :id")
    suspend fun getTypeById(id: Int): TypeEntity?

    @Query("SELECT * FROM type WHERE name = :name LIMIT 1")
    suspend fun getTypeByName(name: String): TypeEntity?

    @Query("SELECT * FROM type ORDER BY id ASC")
    suspend fun getAllTypes(): List<TypeEntity>
}