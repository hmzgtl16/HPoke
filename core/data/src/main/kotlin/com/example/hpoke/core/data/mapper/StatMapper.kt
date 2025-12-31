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

import com.example.hpoke.core.database.model.PokemonWithStat
import com.example.hpoke.core.database.model.StatEntity
import com.example.hpoke.core.model.Stat
import com.example.hpoke.core.network.dto.StatDto

fun PokemonWithStat.asModel() =
    Stat(
        id = stat.id,
        name = stat.name,
        baseStat = pokemonStatCrossRef.baseStat,
        effort = pokemonStatCrossRef.effort,
    )

fun List<PokemonWithStat>.asModel() = map(PokemonWithStat::asModel)

fun StatDto.asEntity() =
    StatEntity(
        id = id,
        name = name,
    )
