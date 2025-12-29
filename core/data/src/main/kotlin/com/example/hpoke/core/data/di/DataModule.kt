package com.example.hpoke.core.data.di

import com.example.hpoke.core.data.mediator.PokemonRemoteMediator
import com.example.hpoke.core.data.repository.OfflineFirstPokemonRepository
import com.example.hpoke.core.data.repository.PokemonRepository
import org.koin.dsl.module

val dataModule = module {

    single<PokemonRepository> { OfflineFirstPokemonRepository() }

    single { PokemonRemoteMediator() }
}