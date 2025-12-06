package com.example.hpoke.core.database.di

import com.example.hpoke.core.database.HPokeDatabase
import org.koin.dsl.module

val daoModule = module {

    single { get<HPokeDatabase>().pokemonDao() }
    single { get<HPokeDatabase>().speciesDao() }
    single { get<HPokeDatabase>().typeDao() }
    single { get<HPokeDatabase>().abilityDao() }
    single { get<HPokeDatabase>().statDao() }
}