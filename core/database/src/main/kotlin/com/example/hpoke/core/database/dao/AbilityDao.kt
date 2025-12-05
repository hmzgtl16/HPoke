package com.example.hpoke.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hpoke.core.database.model.AbilityEntity

@Dao
interface AbilityDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertAbility(ability: AbilityEntity)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertAbilities(abilities: List<AbilityEntity>)

    @Query("SELECT * FROM ability WHERE id = :id")
    suspend fun getAbilityById(id: Int): AbilityEntity?

    @Query("SELECT * FROM ability WHERE name = :name LIMIT 1")
    suspend fun getAbilityByName(name: String): AbilityEntity?

    @Query("SELECT * FROM ability ORDER BY id ASC")
    suspend fun getAllAbilities(): List<AbilityEntity>
}