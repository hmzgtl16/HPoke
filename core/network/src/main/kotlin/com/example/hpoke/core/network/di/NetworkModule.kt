package com.example.hpoke.core.network.di

import com.example.hpoke.core.network.api.PokemonApi
import com.example.hpoke.core.network.api.PokemonApiImpl
import com.example.hpoke.core.network.ktor.httpClient
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkModule = module {

    singleOf(::httpClient)

    singleOf(::PokemonApiImpl) { bind<PokemonApi>() }
}