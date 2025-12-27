package com.example.hpoke.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hpoke.core.database.model.AbilityEntity

@Dao
interface AbilityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAbilities(abilities: List<AbilityEntity>): List<Long>

    @Query(value = "SELECT * FROM ability")
    suspend fun getAllAbilities(): List<AbilityEntity>

    @Query(value = "SELECT * FROM ability WHERE name = :name")
    suspend fun getAbilityByName(name: String): AbilityEntity?
}
