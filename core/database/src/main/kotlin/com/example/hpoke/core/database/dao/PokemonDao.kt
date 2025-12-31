/*
 * Copyright (C) 2025 Hamza Gattal
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.hpoke.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.hpoke.core.database.model.PokemonAbilityCrossRef
import com.example.hpoke.core.database.model.PokemonEntity
import com.example.hpoke.core.database.model.PokemonFull
import com.example.hpoke.core.database.model.PokemonStatCrossRef
import com.example.hpoke.core.database.model.PokemonTypeCrossRef
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Upsert
    suspend fun insertPokemons(pokemons: List<PokemonEntity>): List<Long>

    @Upsert
    suspend fun insertPokemonTypeCrossRefs(refs: List<PokemonTypeCrossRef>)

    @Upsert
    suspend fun insertPokemonAbilityCrossRefs(refs: List<PokemonAbilityCrossRef>)

    @Upsert
    suspend fun insertPokemonStatCrossRefs(refs: List<PokemonStatCrossRef>)

    @Transaction
    @Query("SELECT * FROM pokemon WHERE id = :id")
    fun getPokemonById(id: Int): Flow<PokemonFull>

    @Transaction
    @Query("SELECT * FROM pokemon ORDER BY id ASC")
    fun getAllPokemon(): PagingSource<Int, PokemonFull>

    @Query("SELECT id FROM pokemon")
    suspend fun getAllPokemonIds(): List<Int>

    @Query("SELECT COUNT(*) FROM pokemon_stat_cross_ref WHERE pokemon_id = :pokemonId")
    suspend fun countStatsForPokemon(pokemonId: Int): Int

    @Query("SELECT COUNT(*) FROM pokemon_type_cross_ref WHERE pokemon_id = :pokemonId")
    suspend fun countTypesForPokemon(pokemonId: Int): Int

    @Query("SELECT COUNT(*) FROM pokemon_ability_cross_ref WHERE pokemon_id = :pokemonId")
    suspend fun countAbilitiesForPokemon(pokemonId: Int): Int

    @Query(
        """
        SELECT p.id
        FROM pokemon p
        LEFT JOIN pokemon_stat_cross_ref ps ON ps.pokemon_id = p.id
        GROUP BY p.id
        HAVING COUNT(ps.stat_id) < 6
    """,
    )
    suspend fun pokemonIdsMissingStats(): List<Int>

    @Query(
        """
        SELECT p.id
        FROM pokemon p
        LEFT JOIN pokemon_type_cross_ref pt ON pt.pokemon_id = p.id
        GROUP BY p.id
        HAVING COUNT(pt.type_id) < 1
    """,
    )
    suspend fun pokemonIdsMissingTypes(): List<Int>

    @Query(
        """
        SELECT p.id
        FROM pokemon p
        LEFT JOIN pokemon_ability_cross_ref pa ON pa.pokemon_id = p.id
        GROUP BY p.id
        HAVING COUNT(pa.ability_id) < 1
    """,
    )
    suspend fun pokemonIdsMissingAbilities(): List<Int>
}
