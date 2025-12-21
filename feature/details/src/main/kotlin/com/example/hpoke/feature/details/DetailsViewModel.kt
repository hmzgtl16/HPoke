package com.example.hpoke.feature.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hpoke.core.data.repository.PokemonRepository
import com.example.hpoke.core.model.Pokemon
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailsViewModel(
    pokemonId: Int,
    pokemonRepository: PokemonRepository,
) : ViewModel() {

    val uiState: StateFlow<DetailsUiState> = pokemonRepository
        .getPokemon(id = pokemonId)
        .map<Pokemon, DetailsUiState>(DetailsUiState::Success)
        .catch {
            emit(DetailsUiState.Error)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
            initialValue = DetailsUiState.Loading
        )
}
