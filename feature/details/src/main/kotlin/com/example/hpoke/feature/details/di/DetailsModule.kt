package com.example.hpoke.feature.details.di

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation3.SupportingPaneSceneStrategy
import com.example.hpoke.core.navigation.Navigator
import com.example.hpoke.core.navigation.Route
import com.example.hpoke.feature.details.DetailsScreen
import com.example.hpoke.feature.details.DetailsViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

@OptIn(KoinExperimentalAPI::class, ExperimentalMaterial3AdaptiveApi::class)
val detailsModule = module {

    viewModel {
        DetailsViewModel(pokemonId = it.get(), pokemonRepository = get())
    }
    navigation<Route.Details>(
        metadata = SupportingPaneSceneStrategy.supportingPane()
    ) {
        DetailsScreen(
            viewModel = koinViewModel { parametersOf(it.id) },
            onBackClick = get<Navigator>()::navigateUp
        )
    }
}
