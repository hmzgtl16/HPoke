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
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.hpoke.core.designsystem.component.HPokeCircularProgressIndicator
import com.example.hpoke.core.designsystem.component.HPokeTopAppBar
import com.example.hpoke.core.designsystem.theme.HPokeTheme
import com.example.hpoke.core.model.Pokemon
import com.example.hpoke.core.ui.PokemonCard
import com.example.hpoke.core.ui.preview.PokemonPreviewParameterProvider

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {

    val uiState by viewModel.pokemons.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState,
        modifier = modifier
    )
}

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        when (uiState) {
            is HomeUiState.Loading -> {
                HomeScreenLoading(
                    modifier = Modifier.fillMaxSize()
                )
            }

            is HomeUiState.Success -> {
                HomeScreenPopulated(
                    pokemons = uiState.pokemons,
                    modifier = Modifier.fillMaxSize()
                )
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
fun HomeScreenPopulated(
    pokemons: List<Pokemon>,
    modifier: Modifier = Modifier
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            HPokeTopAppBar(
                titleRes = R.string.feature_home_app_bar_title
            )
        }
    ) {

        if (pokemons.isEmpty()) {
            HomeScreenEmpty(modifier = Modifier.fillMaxSize())
        }

        if (pokemons.isNotEmpty()) {
            HomeScreenContent(
                pokemons = pokemons,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = it)
            )
        }
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

@Composable
fun HomeScreenContent(
    pokemons: List<Pokemon>,
    modifier: Modifier = Modifier
) {

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(count = 2),
        contentPadding = PaddingValues(all = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(space = 6.dp),
        verticalArrangement = Arrangement.spacedBy(space = 6.dp)
    ) {

        items(
            items = pokemons,
            key = Pokemon::id
        ) {

            PokemonCard(
                pokemon = it,
                onPokemonClick = {},
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@PreviewScreenSizes
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
}
