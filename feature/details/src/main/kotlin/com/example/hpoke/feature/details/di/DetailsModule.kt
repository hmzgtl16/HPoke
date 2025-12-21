package com.example.hpoke.feature.details.di

import com.example.hpoke.core.navigation.Details
import com.example.hpoke.feature.details.DetailsScreen
import com.example.hpoke.feature.details.DetailsViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

@OptIn(KoinExperimentalAPI::class)
val detailsModule = module {

    viewModel {
        DetailsViewModel(pokemonId = it.get(), pokemonRepository = get())
    }
    navigation<Details> {
        DetailsScreen(viewModel = koinViewModel { parametersOf(it.id) })
    }
}
