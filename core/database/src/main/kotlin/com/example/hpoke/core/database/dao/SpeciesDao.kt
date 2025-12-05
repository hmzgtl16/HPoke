package com.example.hpoke.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hpoke.core.database.model.PokemonSpeciesEntity

@Dao
interface SpeciesDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertSpecies(species: PokemonSpeciesEntity)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertSpeciesList(list: List<PokemonSpeciesEntity>)

    @Query("SELECT * FROM pokemon_species WHERE id = :id")
    suspend fun getSpeciesById(id: Int): PokemonSpeciesEntity?
}