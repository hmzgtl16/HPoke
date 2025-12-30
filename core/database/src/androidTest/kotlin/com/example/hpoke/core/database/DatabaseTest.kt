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

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.hpoke.core.database.dao.AbilityDao
import com.example.hpoke.core.database.dao.PokemonDao
import com.example.hpoke.core.database.dao.SpritesDao
import com.example.hpoke.core.database.dao.StatDao
import com.example.hpoke.core.database.dao.TypeDao
import org.junit.After
import org.junit.Before

abstract class DatabaseTest {
    private lateinit var database: HPokeDatabase
    protected lateinit var pokemonDao: PokemonDao
    protected lateinit var spritesDao: SpritesDao
    protected lateinit var statDao: StatDao
    protected lateinit var typeDao: TypeDao
    protected lateinit var abilityDao: AbilityDao

    @Before
    open fun setUp() {
        database =
            run {
                val context = ApplicationProvider.getApplicationContext<Context>()
                Room.inMemoryDatabaseBuilder(
                    context,
                    HPokeDatabase::class.java,
                ).build()
            }
        pokemonDao = database.pokemonDao()
        spritesDao = database.speciesDao()
        statDao = database.statDao()
        typeDao = database.typeDao()
        abilityDao = database.abilityDao()
    }

    @After
    open fun tearDown() {
        database.close()
    }
}
