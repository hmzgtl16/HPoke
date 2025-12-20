package com.example.hpoke.feature.home

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.hpoke.core.model.Pokemon
import com.example.hpoke.core.testing.data.pokemonsTestData
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test

class HomeScreenComposeTest {

    @get:Rule
    val composeTestRule =
        createAndroidComposeRule<ComponentActivity>()

    @Test
    fun testHomeScreenLoadingState() {
        composeTestRule.setContent {
            HomeScreen(
                pokemons = flowOf(
                    PagingData.from(
                        data = pokemonsTestData,
                        sourceLoadStates = LoadStates(
                            refresh = LoadState.Loading,
                            append = LoadState.Loading,
                            prepend = LoadState.Loading
                        )
                    )
                ).collectAsLazyPagingItems()
            )
        }

        // Verify loading indicator is displayed
        composeTestRule
            .onNodeWithTag(testTag = "homeScreenLoading")
            .assertExists()
    }

    @Test
    fun testHomeScreenEmptyState() {
        composeTestRule.setContent {
            HomeScreen(
                pokemons = flowOf(
                    PagingData.empty<Pokemon>(
                        sourceLoadStates = LoadStates(
                            refresh = LoadState.NotLoading(endOfPaginationReached = true),
                            append = LoadState.NotLoading(endOfPaginationReached = true),
                            prepend = LoadState.NotLoading(endOfPaginationReached = true)
                        )
                    )
                ).collectAsLazyPagingItems()
            )
        }

        composeTestRule
            .onNodeWithTag(testTag = "homeScreenEmpty")
            .assertExists()
    }

    @Test
    fun testHomeScreenErrorState() {
        composeTestRule.setContent {
            HomeScreen(
                pokemons = flowOf(
                    PagingData.empty<Pokemon>(
                        sourceLoadStates = LoadStates(
                            refresh = LoadState.Error(error = Exception("Error loading data")),
                            append = LoadState.Error(error = Exception("Error loading data")),
                            prepend = LoadState.Error(error = Exception("Error loading data"))
                        )
                    )
                ).collectAsLazyPagingItems()
            )
        }

        composeTestRule
            .onNodeWithTag(testTag = "homeScreenError")
            .assertExists()

        composeTestRule
            .onNodeWithText("Error loading data")
            .assertExists()
    }

    @Test
    fun testHomeScreenGridWithPokemons() {
        composeTestRule.setContent {
            HomeScreen(
                pokemons = flowOf(
                    PagingData.from(
                        data = pokemonsTestData,
                        sourceLoadStates = LoadStates(
                            refresh = LoadState.NotLoading(endOfPaginationReached = true),
                            append = LoadState.NotLoading(endOfPaginationReached = true),
                            prepend = LoadState.NotLoading(endOfPaginationReached = true)
                        )
                    )
                ).collectAsLazyPagingItems()
            )
        }
    }
}
