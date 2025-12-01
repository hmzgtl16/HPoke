package com.example.hpoke.core.network.api

import com.example.hpoke.core.network.model.PaginatedPokemonResponse
import com.example.hpoke.core.network.model.PokemonDetailResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PokemonApiImpl : PokemonApi, KoinComponent {

    val client: HttpClient by inject()

    override suspend fun getPokemonList(
        limit: Int,
        offset: Int
    ): PaginatedPokemonResponse = client
        .get("pokemon?limit=$limit&offset=$offset")
        .body()

    override suspend fun getPokemonDetail(id: Int): PokemonDetailResponse = client
        .get("pokemon/$id")
        .body()
}