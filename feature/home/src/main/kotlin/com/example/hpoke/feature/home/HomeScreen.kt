package com.example.hpoke.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.hpoke.core.designsystem.component.HPokeCircularProgressIndicator
import com.example.hpoke.core.designsystem.component.HPokeTopAppBar
import com.example.hpoke.core.model.Pokemon
import com.example.hpoke.core.ui.PokemonCard

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

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
            columns = GridCells.Fixed(count = 2),
            contentPadding = PaddingValues(all = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(space = 6.dp),
            verticalArrangement = Arrangement.spacedBy(space = 6.dp)
        ) {

            items(
                count = pokemons.itemCount,
                key = { pokemons[it]?.id ?: it }
            ) {
                pokemons[it]?.let { pokemon ->
                    PokemonCard(
                        pokemon = pokemon,
                        onPokemonClick = {},
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            // ⬇️ Append loading / error
            item(
                span = { GridItemSpan(maxLineSpan) }
            ) {
                when (val state = pokemons.loadState.append) {
                    is LoadState.Loading -> {
                        HomeScreenLoading(modifier = Modifier.fillMaxWidth())
                    }

                    is LoadState.Error -> {

                    }

                    else -> Unit
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
        Text(text = "No pokemons found")
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

@PreviewScreenSizes
@Composable
fun HomeScreenContentPreview(
    @PreviewParameter(PokemonPreviewParameterProvider::class) pokemons: List<Pokemon>
) {

    HPokeTheme {
        HomeScreen(
            uiState = HomeUiState.Success(pokemons = pokemons),
            modifier = Modifier.fillMaxSize()
        )
    }
}*/
