package com.example.hpoke.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hpoke.core.database.model.StatEntity

@Dao
interface StatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStats(stats: List<StatEntity>): List<Long>

    @Query(value = "SELECT * FROM stat")
    suspend fun getAllStats(): List<StatEntity>

    @Query(value = "SELECT * FROM stat WHERE name = :name")
    suspend fun getStatByName(name: String): StatEntity?
}