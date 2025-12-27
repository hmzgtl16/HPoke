package com.example.hpoke.core.navigation

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.navigation3.runtime.NavBackStack
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
class NavigatorImpl : Navigator, KoinComponent {

    private val startDestination: Route by inject(named<Route.Home>())

    override val backStack: NavBackStack<Route> = NavBackStack(startDestination)

    override fun navigate(destination: Route) {
        backStack.add(destination)
    }

    override fun navigateUp() {
        backStack.removeLastOrNull()
    }
}