package com.example.hpoke.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hpoke.core.database.dao.AbilityDao
import com.example.hpoke.core.database.dao.MoveDao
import com.example.hpoke.core.database.dao.PokemonDao
import com.example.hpoke.core.database.dao.SpeciesDao
import com.example.hpoke.core.database.dao.StatDao
import com.example.hpoke.core.database.dao.TypeDao
import com.example.hpoke.core.database.model.AbilityEntity
import com.example.hpoke.core.database.model.MoveEntity
import com.example.hpoke.core.database.model.PokemonAbilityCrossRef
import com.example.hpoke.core.database.model.PokemonEntity
import com.example.hpoke.core.database.model.PokemonHeldItemCrossRef
import com.example.hpoke.core.database.model.PokemonMoveCrossRef
import com.example.hpoke.core.database.model.PokemonStatEntity
import com.example.hpoke.core.database.model.PokemonTypeCrossRef
import com.example.hpoke.core.database.model.SpeciesEntity
import com.example.hpoke.core.database.model.StatEntity
import com.example.hpoke.core.database.model.TypeEntity

@Database(
    entities = [
        PokemonEntity::class,
        SpeciesEntity::class,
        TypeEntity::class,
        PokemonTypeCrossRef::class,
        AbilityEntity::class,
        PokemonAbilityCrossRef::class,
        MoveEntity::class,
        PokemonMoveCrossRef::class,
        StatEntity::class,
        PokemonStatEntity::class,
        PokemonHeldItemCrossRef::class
    ],
    version = 1,
    exportSchema = true
)
abstract class HPokeDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
    abstract fun speciesDao(): SpeciesDao
    abstract fun typeDao(): TypeDao
    abstract fun abilityDao(): AbilityDao
    abstract fun moveDao(): MoveDao
    abstract fun statDao(): StatDao
}