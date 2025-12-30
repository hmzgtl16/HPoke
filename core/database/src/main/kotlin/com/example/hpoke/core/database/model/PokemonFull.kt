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

package com.example.hpoke.core.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class PokemonFull(
    @Embedded val pokemon: PokemonEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
    )
    val species: SpritesEntity,
    @Relation(
        entity = PokemonStatCrossRef::class,
        parentColumn = "id",
        entityColumn = "pokemon_id",
    )
    val stats: List<PokemonWithStat>,
    @Relation(
        entity = PokemonTypeCrossRef::class,
        parentColumn = "id",
        entityColumn = "pokemon_id",
    )
    val types: List<PokemonWithType>,
    @Relation(
        entity = PokemonAbilityCrossRef::class,
        parentColumn = "id",
        entityColumn = "pokemon_id",
    )
    val abilities: List<PokemonWithAbility>,
)
