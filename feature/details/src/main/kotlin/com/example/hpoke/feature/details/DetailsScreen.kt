package com.example.hpoke.feature.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel
) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    DetailsScreen(
        uiState = uiState.value,
        modifier = modifier
    )
}

@Composable
fun DetailsScreen(
    uiState: DetailsUiState,
    modifier: Modifier = Modifier
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
                Text(uiState.pokemon.name)
            }

            is DetailsUiState.Error -> {
                Text("Error")
            }
        }
    }

}