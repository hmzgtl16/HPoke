package com.example.hpoke.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hpoke.core.database.model.StatEntity

@Dao
interface StatDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertStat(stat: StatEntity)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertStats(stats: List<StatEntity>)

    @Query("SELECT * FROM stat WHERE id = :id")
    suspend fun getStatById(id: Int): StatEntity?

    @Query("SELECT * FROM stat WHERE name = :name LIMIT 1")
    suspend fun getStatByName(name: String): StatEntity?

    @Query("SELECT * FROM stat ORDER BY id ASC")
    suspend fun getAllStats(): List<StatEntity>
}