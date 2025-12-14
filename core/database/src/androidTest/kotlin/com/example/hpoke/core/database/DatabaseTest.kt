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
        database = run {
            val context = ApplicationProvider.getApplicationContext<Context>()
            Room.inMemoryDatabaseBuilder(
                context,
                HPokeDatabase::class.java
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

