package com.example.hpoke.core.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class NavigatorImpl() : Navigator, KoinComponent {

    private val startDestination: NavKey by inject()

    override val backStack: NavBackStack<NavKey> = NavBackStack(startDestination)

    override fun navigate(destination: NavKey) {
        backStack.add(destination)
    }

    override fun navigateUp() {
        backStack.removeLastOrNull()
    }
}