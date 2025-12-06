package com.example.hpoke.core.data.di

import com.example.hpoke.core.data.repository.OfflineFirstPokemonRepository
import com.example.hpoke.core.data.repository.PokemonRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {

    singleOf(::OfflineFirstPokemonRepository) { bind<PokemonRepository>() }
}