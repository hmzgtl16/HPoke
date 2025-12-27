package com.example.hpoke.core.navigation

import androidx.navigation3.runtime.NavBackStack

interface Navigator {

    val backStack: NavBackStack<Route>

    fun navigate(destination: Route)

    fun navigateUp()
}