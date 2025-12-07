package com.example.hpoke.feature.home.di

import androidx.navigation3.runtime.NavKey
import com.example.hpoke.feature.home.HomeScreen
import com.example.hpoke.feature.home.HomeViewModel
import com.example.hpoke.feature.home.navigation.Home
import org.koin.androidx.compose.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

@OptIn(KoinExperimentalAPI::class)
val homeModule = module {
    single<NavKey> { Home }
    viewModelOf(::HomeViewModel)
    navigation<Home> { HomeScreen(viewModel = koinViewModel()) }
}
