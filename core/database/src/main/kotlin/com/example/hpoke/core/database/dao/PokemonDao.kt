package com.example.hpoke.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.hpoke.core.database.model.PokemonAbilityCrossRef
import com.example.hpoke.core.database.model.PokemonEntity
import com.example.hpoke.core.database.model.PokemonFull
import com.example.hpoke.core.database.model.PokemonHeldItemCrossRef
import com.example.hpoke.core.database.model.PokemonMoveCrossRef
import com.example.hpoke.core.database.model.PokemonStatEntity
import com.example.hpoke.core.database.model.PokemonTypeCrossRef
import kotlinx.coroutines.flow.Flow

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

    @Transaction
    @Query("SELECT * FROM pokemon WHERE id = :id")
    fun getPokemonById(id: Int): Flow<PokemonFull?>

    @Query("SELECT * FROM pokemon WHERE name = :name LIMIT 1")
    suspend fun getPokemonByName(name: String): PokemonEntity?

    @Transaction
    @Query("SELECT * FROM pokemon ORDER BY id ASC")
    fun getAllPokemon(): Flow<List<PokemonFull>>

    @Query("DELETE FROM pokemon")
    suspend fun clearPokemon()


    // RELATIONS

}
