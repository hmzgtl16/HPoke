/*
 * Copyright (C) 2025 Hamza Gattal
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.hpoke.core.network.api

import com.example.hpoke.core.network.dto.AbilityDto
import com.example.hpoke.core.network.dto.NamedApiResourceListDto
import com.example.hpoke.core.network.dto.PokemonDto
import com.example.hpoke.core.network.dto.StatDto
import com.example.hpoke.core.network.dto.TypeDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PokemonApiImpl :
    PokemonApi,
    KoinComponent {
    val client: HttpClient by inject()

    override suspend fun getPokemonList(
        offset: Int,
        limit: Int,
    ): NamedApiResourceListDto =
        client
            .get("pokemon") {
                parameter("offset", offset)
                parameter("limit", limit)
            }
            .body()

    override suspend fun getPokemon(name: String): PokemonDto =
        client
            .get("pokemon/$name")
            .body()

    override suspend fun getAbility(name: String): AbilityDto =
        client
            .get("ability/$name")
            .body()

    override suspend fun getStat(name: String): StatDto =
        client
            .get("stat/$name")
            .body()

    override suspend fun getType(name: String): TypeDto =
        client
            .get("type/$name")
            .body()
}
