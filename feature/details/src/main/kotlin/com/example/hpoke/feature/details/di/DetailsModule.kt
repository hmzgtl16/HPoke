package com.example.hpoke.feature.details.di

import androidx.navigation3.runtime.NavKey
import com.example.hpoke.feature.details.navigation.Details
import org.koin.dsl.module

val detailsModule = module {
    single<NavKey> { Details }
}