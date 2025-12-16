package com.example.hpoke.feature.home

import com.example.hpoke.core.model.Pokemon

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Success(val pokemons: List<Pokemon>) : HomeUiState
}