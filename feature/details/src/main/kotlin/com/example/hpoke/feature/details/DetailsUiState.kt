package com.example.hpoke.feature.details

import com.example.hpoke.core.model.Pokemon

sealed interface DetailsUiState {

    data object Loading : DetailsUiState
    data object Error : DetailsUiState
    data class Success(val pokemon: Pokemon) : DetailsUiState
}