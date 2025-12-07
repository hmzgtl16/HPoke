package com.example.hpoke.core.navigation.di

import com.example.hpoke.core.navigation.Navigator
import com.example.hpoke.core.navigation.NavigatorImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val navigationModule = module {
    singleOf(::NavigatorImpl) { bind<Navigator>() }
}