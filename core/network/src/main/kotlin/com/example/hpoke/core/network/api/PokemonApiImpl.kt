package com.example.hpoke.core.network.api

import com.example.hpoke.core.network.dto.AbilityDto
import com.example.hpoke.core.network.dto.MoveDto
import com.example.hpoke.core.network.dto.PokemonDto
import com.example.hpoke.core.network.dto.PokemonListDto
import com.example.hpoke.core.network.dto.StatDto
import com.example.hpoke.core.network.dto.TypeDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PokemonApiImpl : PokemonApi, KoinComponent {

    val client: HttpClient by inject()

    override suspend fun getPokemonList(
        offset: Int,
        limit: Int
    ): PokemonListDto = client
        .get("pokemon") {
            parameter("offset", offset)
            parameter("limit", limit)
        }
        .body()

    override suspend fun getPokemon(id: Int): PokemonDto = client
        .get("pokemon/$id")
        .body()

    override suspend fun getAbility(id: Int): AbilityDto = client
        .get("ability/$id")
        .body()

    override suspend fun getMove(id: Int): MoveDto = client
        .get("move/$id")
        .body()

    override suspend fun getStat(id: Int): StatDto = client
        .get("stat/$id")
        .body()

    override suspend fun getType(id: Int): TypeDto = client
        .get("type/$id")
        .body()
}