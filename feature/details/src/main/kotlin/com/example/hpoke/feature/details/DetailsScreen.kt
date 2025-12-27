package com.example.hpoke.feature.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.hpoke.core.designsystem.component.HPokeImage
import com.example.hpoke.core.designsystem.component.HPokeTopAppBar
import com.example.hpoke.core.designsystem.icon.HPokeIcons
import com.example.hpoke.core.designsystem.theme.HPokeTheme
import com.example.hpoke.core.model.Pokemon
import com.example.hpoke.core.ui.PokemonAbilitiesCard
import com.example.hpoke.core.ui.PokemonInfoCard
import com.example.hpoke.core.ui.PokemonStats
import com.example.hpoke.core.ui.PokemonTypesCard
import com.example.hpoke.core.ui.palette.generatePalette
import com.example.hpoke.core.ui.palette.paletteBackgroundColor
import com.example.hpoke.core.ui.palette.paletteTextColor
import com.example.hpoke.core.ui.palette.rememberPaletteState
import com.example.hpoke.core.ui.preview.PokemonPreviewParameterProvider

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel,
    onBackClick: () -> Unit
) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    DetailsScreen(
        uiState = uiState.value,
        modifier = modifier,
        onBackClick = onBackClick
    )
}

@Composable
fun DetailsScreen(
    uiState: DetailsUiState,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (uiState) {
            is DetailsUiState.Loading -> {
                Text("Loading...")
            }

            is DetailsUiState.Success -> {
                DetailsScreenContent(
                    pokemon = uiState.pokemon,
                    onBackClick = onBackClick,
                    modifier = Modifier.fillMaxSize()
                )
            }

            is DetailsUiState.Error -> {
                Text("Error")
            }
        }
    }
}

@Composable
fun DetailsScreenContent(
    pokemon: Pokemon,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    var palette by rememberPaletteState()
    val backgroundColor by palette.paletteBackgroundColor()
    val textColor by palette.paletteTextColor()

    Scaffold(
        topBar = {
            HPokeTopAppBar(
                title = pokemon.name.replaceFirstChar(Char::titlecase),
                navigationIcon = HPokeIcons.Back,
                navigationIconContentDescription = "Back",
                onNavigationClick = onBackClick,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = backgroundColor,
                    titleContentColor = textColor,
                    navigationIconContentColor = textColor
                )
            )
        },
        modifier = modifier
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
            verticalArrangement = Arrangement.spacedBy(
                space = 16.dp,
                alignment = Alignment.Top
            )
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = backgroundColor),
                contentAlignment = Alignment.Center
            ) {
                HPokeImage(
                    imageUrl = pokemon.species.frontDefault,
                    contentDescription = pokemon.name,
                    onBitmapReady = { palette = it.generatePalette() },
                    modifier = Modifier.padding(all = 16.dp)
                )
            }

            PokemonInfoCard(
                height = pokemon.height,
                weight = pokemon.weight,
                baseExperience = pokemon.baseExperience,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            PokemonTypesCard(
                types = pokemon.types,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            PokemonAbilitiesCard(
                abilities = pokemon.abilities,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            PokemonStats(
                stats = pokemon.stats,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }
    }
}

@PreviewScreenSizes
@Composable
fun DetailsScreenLoadingPreview() {
    HPokeTheme {
        DetailsScreen(
            uiState = DetailsUiState.Loading,
            onBackClick = {}
        )
    }
}

@PreviewScreenSizes
@Composable
fun DetailsScreenErrorPreview() {
    HPokeTheme {
        DetailsScreen(
            uiState = DetailsUiState.Error,
            onBackClick = {}
        )
    }
}

@PreviewScreenSizes
@Composable
fun DetailsScreenContentPreview(
    @PreviewParameter(PokemonPreviewParameterProvider::class) pokemons: List<Pokemon>
) {
    HPokeTheme {
        DetailsScreen(
            uiState = DetailsUiState.Success(pokemon = pokemons.first()),
            onBackClick = {}
        )
    }
}
