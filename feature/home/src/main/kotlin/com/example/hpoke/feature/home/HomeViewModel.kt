package com.example.hpoke.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hpoke.core.data.repository.PokemonRepository
import com.example.hpoke.core.model.Pokemon
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    pokemonRepository: PokemonRepository
) : ViewModel() {

    val pokemons: StateFlow<HomeUiState> = pokemonRepository.pokemons
        .map<List<Pokemon>, HomeUiState>(HomeUiState::Success)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
            initialValue = HomeUiState.Loading
        )
}

