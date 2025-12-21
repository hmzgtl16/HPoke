package com.example.hpoke.feature.home.di

import com.example.hpoke.core.navigation.Details
import com.example.hpoke.core.navigation.Home
import com.example.hpoke.core.navigation.Navigator
import com.example.hpoke.feature.home.HomeScreen
import com.example.hpoke.feature.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

@OptIn(KoinExperimentalAPI::class)
val homeModule = module {
    viewModel { HomeViewModel(pokemonRepository = get()) }
    navigation<Home> {
        HomeScreen(
            viewModel = koinViewModel(),
            onPokemonClick = { get<Navigator>().navigate(Details(id = it)) }
        )
    }
}
