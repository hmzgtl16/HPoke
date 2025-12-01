package com.example.hpoke.core.network.api

import com.example.hpoke.core.network.model.PaginatedPokemonResponse
import com.example.hpoke.core.network.model.PokemonDetailResponse

interface PokemonApi {

    suspend fun getPokemonList(limit: Int, offset: Int): PaginatedPokemonResponse

    suspend fun getPokemonDetail(id: Int): PokemonDetailResponse
}