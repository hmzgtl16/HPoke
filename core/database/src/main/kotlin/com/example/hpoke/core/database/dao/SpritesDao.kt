package com.example.hpoke.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.hpoke.core.database.model.SpritesEntity

@Dao
interface SpritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSprites(sprites: List<SpritesEntity>): List<Long>
}