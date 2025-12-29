package com.example.hpoke.core.navigation.di

import com.example.hpoke.core.navigation.Navigator
import com.example.hpoke.core.navigation.NavigatorImpl
import org.koin.dsl.module

val navigationModule = module {
    single<Navigator> { NavigatorImpl() }
}