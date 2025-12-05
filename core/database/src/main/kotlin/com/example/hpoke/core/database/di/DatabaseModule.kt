package com.example.hpoke.core.database.di

import androidx.room.Room
import com.example.hpoke.core.database.HPokeDatabase
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            context = get(),
            klass = HPokeDatabase::class.java,
            name = "hpoke_database"
        )
            .build()
    }
}
