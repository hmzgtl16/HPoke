package com.example.hpoke.feature.home

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.hpoke.core.designsystem.component.HPokeCircularProgressIndicator
import com.example.hpoke.core.designsystem.component.HPokeTopAppBar
import com.example.hpoke.core.designsystem.theme.HPokeTheme
import com.example.hpoke.core.model.Pokemon
import com.example.hpoke.core.ui.PokemonCard
import com.example.hpoke.core.ui.preview.PokemonPreviewParameterProvider
import kotlinx.coroutines.flow.flowOf

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    onPokemonClick: (Int) -> Unit
) {

    val pokemons = viewModel.pokemons.collectAsLazyPagingItems()

    HomeScreen(
        pokemons = pokemons,
        modifier = modifier,
        onPokemonClick = onPokemonClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@VisibleForTesting
@Composable
fun HomeScreen(
    pokemons: LazyPagingItems<Pokemon>,
    onPokemonClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(connection = scrollBehavior.nestedScrollConnection),
        topBar = {
            HPokeTopAppBar(
                titleRes = R.string.feature_home_app_bar_title,
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
        ) {
            when (pokemons.loadState.refresh) {
                is LoadState.Loading -> {
                    HomeScreenLoading(modifier = Modifier.fillMaxSize())
                }

                is LoadState.NotLoading if pokemons.itemCount == 0 -> {
                    HomeScreenEmpty(modifier = Modifier.fillMaxSize())
                }

                is LoadState.Error -> {
                    HomeScreenError(
                        message = (pokemons.loadState.refresh as LoadState.Error).error.message,
                        onRetry = { pokemons.retry() },
                        modifier = Modifier.fillMaxSize()
                    )
                }

                else -> {
                    HomeScreenGrid(
                        pokemons = pokemons,
                        onPokemonClick = onPokemonClick,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreenLoading(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .testTag(tag = "homeScreenLoading"),
        contentAlignment = Alignment.Center
    ) {

        HPokeCircularProgressIndicator(
            modifier = Modifier.size(size = 32.dp)
        )
    }
}

@Composable
fun HomeScreenEmpty(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .testTag(tag = "homeScreenEmpty"),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "No pokemons found",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun HomeScreenError(
    message: String?,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .testTag(tag = "homeScreenError"),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 12.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        Text(
            text = message ?: "An error occurred",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Button(
            onClick = onRetry,
            content = { Text(text = "Retry") }
        )
    }
}

@Composable
fun HomeScreenGrid(
    pokemons: LazyPagingItems<Pokemon>,
    onPokemonClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        modifier = modifier
            .testTag(tag = "homeScreenGrid"),
        columns = GridCells.Adaptive(minSize = 140.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
        verticalArrangement = Arrangement.spacedBy(space = 16.dp)
    ) {

        items(
            count = pokemons.itemCount,
            key = { pokemons[it]?.name ?: it }
        ) {
            pokemons[it]?.let { pokemon ->
                PokemonCard(
                    pokemon = pokemon,
                    onPokemonClick = onPokemonClick,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        item(
            span = { GridItemSpan(maxLineSpan) }
        ) {
            when (val state = pokemons.loadState.append) {
                is LoadState.Loading -> {
                    HomeScreenLoading(modifier = Modifier.fillMaxWidth())
                }

                is LoadState.Error -> {
                    HomeScreenError(
                        message = state.error.message,
                        onRetry = pokemons::retry,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                else -> Unit
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun HomeScreenLoadingPreview(
    @PreviewParameter(PokemonPreviewParameterProvider::class) pokemons: List<Pokemon>
) {

    HPokeTheme {
        HomeScreen(
            pokemons = flowOf(
                PagingData.from(
                    data = pokemons,
                    sourceLoadStates = LoadStates(
                        refresh = LoadState.Loading,
                        append = LoadState.Loading,
                        prepend = LoadState.Loading
                    )
                )
            ).collectAsLazyPagingItems(),
            onPokemonClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@PreviewScreenSizes
@Composable
fun HomeScreenEmptyPreview() {
    HPokeTheme {
        HomeScreen(
            pokemons = flowOf(
                PagingData.empty<Pokemon>(
                    sourceLoadStates = LoadStates(
                        refresh = LoadState.NotLoading(endOfPaginationReached = true),
                        append = LoadState.NotLoading(endOfPaginationReached = true),
                        prepend = LoadState.NotLoading(endOfPaginationReached = true)
                    )
                )
            ).collectAsLazyPagingItems(),
            onPokemonClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@PreviewScreenSizes
@Composable
fun HomeScreenErrorPreview() {

    HPokeTheme {
        HomeScreen(
            pokemons = flowOf(
                PagingData.empty<Pokemon>(
                    sourceLoadStates = LoadStates(
                        refresh = LoadState.Error(error = Exception("Error loading data")),
                        append = LoadState.Error(error = Exception("Error loading data")),
                        prepend = LoadState.Error(error = Exception("Error loading data"))
                    )
                )
            ).collectAsLazyPagingItems(),
            onPokemonClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@PreviewScreenSizes
@Composable
fun HomeScreenPreview(
    @PreviewParameter(PokemonPreviewParameterProvider::class) pokemons: List<Pokemon>
) {

    HPokeTheme {
        HomeScreen(
            pokemons = flowOf(
                PagingData.from(
                    data = pokemons.subList(0, 9),
                    sourceLoadStates = LoadStates(
                        refresh = LoadState.NotLoading(endOfPaginationReached = false),
                        append = LoadState.NotLoading(endOfPaginationReached = false),
                        prepend = LoadState.NotLoading(endOfPaginationReached = false)
                    )
                )
            ).collectAsLazyPagingItems(),
            onPokemonClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
