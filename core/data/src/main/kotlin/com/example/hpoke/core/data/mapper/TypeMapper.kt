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

package com.example.hpoke.core.data.mapper

import com.example.hpoke.core.database.model.PokemonWithType
import com.example.hpoke.core.database.model.TypeEntity
import com.example.hpoke.core.model.Type
import com.example.hpoke.core.network.dto.TypeDto

fun PokemonWithType.asModel() =
    Type(
        id = type.id,
        name = type.name,
        slot = pokemonTypeCrossRef.slot,
    )

fun List<PokemonWithType>.asModel() = map(PokemonWithType::asModel)

fun TypeDto.asEntity() =
    TypeEntity(
        id = id,
        name = name,
    )
