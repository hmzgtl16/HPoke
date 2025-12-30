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

interface PokemonApi {
    suspend fun getPokemonList(offset: Int = 0, limit: Int = 20): NamedApiResourceListDto

    suspend fun getPokemon(name: String): PokemonDto

    suspend fun getAbility(name: String): AbilityDto

    suspend fun getStat(name: String): StatDto

    suspend fun getType(name: String): TypeDto
}
