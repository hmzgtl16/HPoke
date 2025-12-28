package com.example.hpoke.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.hpoke.core.data.repository.PokemonRepository
import com.example.hpoke.core.model.Pokemon
import kotlinx.coroutines.flow.Flow

class HomeViewModel(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    val pokemons: Flow<PagingData<Pokemon>>
        get() = pokemonRepository.getPokemons()
            .cachedIn(scope = viewModelScope)
}

