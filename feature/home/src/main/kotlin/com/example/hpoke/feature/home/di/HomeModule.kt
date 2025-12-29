package com.example.hpoke.feature.home.di

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation3.ListDetailSceneStrategy
import com.example.hpoke.core.navigation.Navigator
import com.example.hpoke.core.navigation.Route
import com.example.hpoke.feature.home.HomeScreen
import com.example.hpoke.feature.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

@OptIn(KoinExperimentalAPI::class, ExperimentalMaterial3AdaptiveApi::class)
val homeModule = module {
    single<Route>(qualifier = named<Route.Home>()) { Route.Home }
    viewModel { HomeViewModel(pokemonRepository = get()) }
    navigation<Route.Home>(
        metadata = ListDetailSceneStrategy.listPane()
    ) {
        HomeScreen(
            viewModel = koinViewModel(),
            onPokemonClick = {
                get<Navigator>().navigate(Route.Details(id = it))
            }
        )
    }
}
