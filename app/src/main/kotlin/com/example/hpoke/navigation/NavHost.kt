package com.example.hpoke.navigation

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation3.rememberSupportingPaneSceneStrategy
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.hpoke.core.navigation.Route
import org.koin.compose.navigation3.koinEntryProvider
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class, ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun NavHost(
    modifier: Modifier = Modifier,
    backStack: NavBackStack<Route>,
    onBack: () -> Unit
) {
    val entryProvider = koinEntryProvider()
    val sceneStrategy = rememberSupportingPaneSceneStrategy<Route>()

    @Suppress("UNCHECKED_CAST")
    val routeEntryProvider = entryProvider as (Route) -> NavEntry<Route>

    NavDisplay(
        backStack = backStack,
        modifier = modifier,
        onBack = onBack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        sceneStrategy = sceneStrategy,
        entryProvider = routeEntryProvider
    )
}