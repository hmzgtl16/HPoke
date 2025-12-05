package com.example.hpoke.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.hpoke.core.database.model.MoveEntity
import com.example.hpoke.core.database.model.PokemonEntity

@Dao
interface MoveDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertMove(move: MoveEntity)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertMoves(moves: List<MoveEntity>)

    @Query("SELECT * FROM move WHERE id = :id")
    suspend fun getMoveById(id: Int): MoveEntity?

    @Query("SELECT * FROM move WHERE name = :name LIMIT 1")
    suspend fun getMoveByName(name: String): MoveEntity?

    @Query("SELECT * FROM move ORDER BY id ASC")
    suspend fun getAllMoves(): List<MoveEntity>

    // all Pokémon that can learn a given move
    @Transaction
    @Query(
        """
        SELECT pokemon.* FROM pokemon
        INNER JOIN pokemon_move_cross_ref 
        ON pokemon.id = pokemon_move_cross_ref.pokemonId
        WHERE pokemon_move_cross_ref.moveId = :moveId
    """
    )
    suspend fun getPokemonThatLearnMove(moveId: Int): List<PokemonEntity>
}