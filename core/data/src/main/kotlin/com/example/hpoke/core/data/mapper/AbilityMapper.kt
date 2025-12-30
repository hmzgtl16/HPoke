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

import com.example.hpoke.core.database.model.AbilityEntity
import com.example.hpoke.core.database.model.PokemonWithAbility
import com.example.hpoke.core.model.Ability
import com.example.hpoke.core.network.dto.AbilityDto

fun PokemonWithAbility.asModel() =
    Ability(
        id = ability.id,
        name = ability.name,
        isHidden = pokemonAbilityCrossRef.isHidden,
        slot = pokemonAbilityCrossRef.slot,
    )

fun List<PokemonWithAbility>.asModel() = map(PokemonWithAbility::asModel)

fun AbilityDto.asEntity() =
    AbilityEntity(
        id = id,
        name = name,
    )
