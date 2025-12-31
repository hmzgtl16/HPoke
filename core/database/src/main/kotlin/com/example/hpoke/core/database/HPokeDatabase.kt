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

package com.example.hpoke.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hpoke.core.database.dao.AbilityDao
import com.example.hpoke.core.database.dao.PokemonDao
import com.example.hpoke.core.database.dao.RemoteKeyDao
import com.example.hpoke.core.database.dao.SpritesDao
import com.example.hpoke.core.database.dao.StatDao
import com.example.hpoke.core.database.dao.TypeDao
import com.example.hpoke.core.database.model.AbilityEntity
import com.example.hpoke.core.database.model.PokemonAbilityCrossRef
import com.example.hpoke.core.database.model.PokemonEntity
import com.example.hpoke.core.database.model.PokemonStatCrossRef
import com.example.hpoke.core.database.model.PokemonTypeCrossRef
import com.example.hpoke.core.database.model.RemoteKeyEntity
import com.example.hpoke.core.database.model.SpritesEntity
import com.example.hpoke.core.database.model.StatEntity
import com.example.hpoke.core.database.model.TypeEntity

@Database(
    entities = [
        PokemonEntity::class,
        SpritesEntity::class,
        TypeEntity::class,
        PokemonTypeCrossRef::class,
        AbilityEntity::class,
        PokemonAbilityCrossRef::class,
        StatEntity::class,
        PokemonStatCrossRef::class,
        RemoteKeyEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
abstract class HPokeDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao

    abstract fun speciesDao(): SpritesDao

    abstract fun typeDao(): TypeDao

    abstract fun abilityDao(): AbilityDao

    abstract fun statDao(): StatDao

    abstract fun remoteKeyDao(): RemoteKeyDao
}
