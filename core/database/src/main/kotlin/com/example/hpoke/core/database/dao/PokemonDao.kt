package com.example.hpoke.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.hpoke.core.database.model.PokemonAbilityCrossRef
import com.example.hpoke.core.database.model.PokemonEntity
import com.example.hpoke.core.database.model.PokemonHeldItemCrossRef
import com.example.hpoke.core.database.model.PokemonMoveCrossRef
import com.example.hpoke.core.database.model.PokemonStatEntity
import com.example.hpoke.core.database.model.PokemonTypeCrossRef
import com.example.hpoke.core.database.model.PokemonWithAbilities
import com.example.hpoke.core.database.model.PokemonWithStats
import com.example.hpoke.core.database.model.PokemonWithTypes

@Dao
interface PokemonDao {

    // INSERTS

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon: PokemonEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemons(pokemons: List<PokemonEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonTypeCrossRefs(refs: List<PokemonTypeCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonAbilityCrossRefs(refs: List<PokemonAbilityCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonMoveCrossRefs(refs: List<PokemonMoveCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonStats(stats: List<PokemonStatEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonHeldItems(refs: List<PokemonHeldItemCrossRef>)


    // BASIC QUERIES

    @Query("SELECT * FROM pokemon WHERE id = :id")
    suspend fun getPokemonById(id: Int): PokemonEntity?

    @Query("SELECT * FROM pokemon WHERE name = :name LIMIT 1")
    suspend fun getPokemonByName(name: String): PokemonEntity?

    @Query("SELECT * FROM pokemon ORDER BY id ASC")
    suspend fun getAllPokemon(): List<PokemonEntity>

    @Query("DELETE FROM pokemon")
    suspend fun clearPokemon()


    // RELATIONS

    @Transaction
    @Query("SELECT * FROM pokemon WHERE id = :id")
    suspend fun getPokemonWithTypes(id: Int): PokemonWithTypes?

    @Transaction
    @Query("SELECT * FROM pokemon WHERE id = :id")
    suspend fun getPokemonWithAbilities(id: Int): PokemonWithAbilities?

    @Transaction
    @Query("SELECT * FROM pokemon WHERE id = :id")
    suspend fun getPokemonWithStats(id: Int): PokemonWithStats?
}
