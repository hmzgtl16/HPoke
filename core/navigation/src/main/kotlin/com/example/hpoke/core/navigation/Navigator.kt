package com.example.hpoke.core.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

interface Navigator {

    val backStack: NavBackStack<NavKey>

    fun navigate(destination: NavKey)

    fun navigateUp()
}