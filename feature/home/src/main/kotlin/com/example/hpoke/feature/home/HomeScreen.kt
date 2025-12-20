package com.example.hpoke.feature.home

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
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
    viewModel: HomeViewModel
) {

    val pokemons = viewModel.pokemons.collectAsLazyPagingItems()

    HomeScreen(
        pokemons = pokemons,
        modifier = modifier
    )
}

@Composable
fun HomeScreen(
    pokemons: LazyPagingItems<Pokemon>,
    modifier: Modifier = Modifier,
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            HPokeTopAppBar(
                titleRes = R.string.feature_home_app_bar_title
            )
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
        ) {
            when (pokemons.loadState.refresh) {
                is LoadState.NotLoading if pokemons.itemCount == 0 -> {
                    HomeScreenEmpty(modifier = Modifier.fillMaxSize())
                }

                is LoadState.Loading -> {
                    HomeScreenLoading(modifier = Modifier.fillMaxSize())
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
                        onPokemonClick = {},
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
        modifier = modifier,
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
        modifier = modifier,
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
        modifier = modifier,
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
        modifier = modifier,
        columns = GridCells.Adaptive(minSize = 128.dp),
        contentPadding = PaddingValues(all = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
        verticalArrangement = Arrangement.spacedBy(space = 8.dp)
    ) {

        items(
            count = pokemons.itemCount,
            key = { pokemons[it]?.id ?: it }
        ) {
            pokemons[it]?.let { pokemon ->
                PokemonCard(
                    pokemon = pokemon,
                    onPokemonClick = onPokemonClick,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        // Footer: loading / error while paging
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

/*@PreviewScreenSizes
@Composable
fun HomeScreenLoadingPreview() {

    HPokeTheme {
        HomeScreen(
            uiState = HomeUiState.Loading,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@PreviewScreenSizes
@Composable
fun HomeScreenEmptyPreview() {

    HPokeTheme {
        HomeScreen(
            uiState = HomeUiState.Success(pokemons = emptyList()),
            modifier = Modifier.fillMaxSize()
        )
    }
}
*/

@PreviewScreenSizes
@Composable
fun HomeScreenPreview(
    @PreviewParameter(PokemonPreviewParameterProvider::class) pokemons: List<Pokemon>
) {

    HPokeTheme {
        HomeScreen(
            pokemons = flowOf(PagingData.from(pokemons)).collectAsLazyPagingItems(),
            modifier = Modifier.fillMaxSize()
        )
    }
}
