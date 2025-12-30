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

package com.example.hpoke.core.ui

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color

enum class PokemonType(
    val apiName: String,
    @StringRes val labelRes: Int,
    val backgroundColor: Color,
    val textColor: Color,
) {
    NORMAL(
        apiName = "normal",
        labelRes = R.string.core_ui_type_normal,
        backgroundColor = Color(0xFFBDBDBD),
        textColor = Color.Black,
    ),
    FIGHTING(
        apiName = "fighting",
        labelRes = R.string.core_ui_type_fighting,
        backgroundColor = Color(0xFFD32F2F),
        textColor = Color.White,
    ),
    FLYING(
        apiName = "flying",
        labelRes = R.string.core_ui_type_flying,
        backgroundColor = Color(0xFF90CAF9),
        textColor = Color.Black,
    ),
    POISON(
        apiName = "poison",
        labelRes = R.string.core_ui_type_poison,
        backgroundColor = Color(0xFFBA68C8),
        textColor = Color.White,
    ),
    GROUND(
        apiName = "ground",
        labelRes = R.string.core_ui_type_ground,
        backgroundColor = Color(0xFFFFB74D),
        textColor = Color.Black,
    ),
    ROCK(
        apiName = "rock",
        labelRes = R.string.core_ui_type_rock,
        backgroundColor = Color(0xFFBCAAA4),
        textColor = Color.Black,
    ),
    BUG(
        apiName = "bug",
        labelRes = R.string.core_ui_type_bug,
        backgroundColor = Color(0xFFAED581),
        textColor = Color.Black,
    ),
    GHOST(
        apiName = "ghost",
        labelRes = R.string.core_ui_type_ghost,
        backgroundColor = Color(0xFF7E57C2),
        textColor = Color.White,
    ),
    STEEL(
        apiName = "steel",
        labelRes = R.string.core_ui_type_steel,
        backgroundColor = Color(0xFFB0BEC5),
        textColor = Color.Black,
    ),
    FIRE(
        apiName = "fire",
        labelRes = R.string.core_ui_type_fire,
        backgroundColor = Color(0xFFFF7043),
        textColor = Color.White,
    ),
    WATER(
        apiName = "water",
        labelRes = R.string.core_ui_type_water,
        backgroundColor = Color(0xFF42A5F5),
        textColor = Color.White,
    ),
    GRASS(
        apiName = "grass",
        labelRes = R.string.core_ui_type_grass,
        backgroundColor = Color(0xFF9CCC65),
        textColor = Color.Black,
    ),
    ELECTRIC(
        apiName = "electric",
        labelRes = R.string.core_ui_type_electric,
        backgroundColor = Color(0xFFFFD54F),
        textColor = Color.Black,
    ),
    PSYCHIC(
        apiName = "psychic",
        labelRes = R.string.core_ui_type_psychic,
        backgroundColor = Color(0xFFEC407A),
        textColor = Color.White,
    ),
    ICE(
        apiName = "ice",
        labelRes = R.string.core_ui_type_ice,
        backgroundColor = Color(0xFF4DD0E1),
        textColor = Color.Black,
    ),
    DRAGON(
        apiName = "dragon",
        labelRes = R.string.core_ui_type_dragon,
        backgroundColor = Color(0xFF5C6BC0),
        textColor = Color.White,
    ),
    DARK(
        apiName = "dark",
        labelRes = R.string.core_ui_type_dark,
        backgroundColor = Color(0xFF616161),
        textColor = Color.White,
    ),
    FAIRY(
        apiName = "fairy",
        labelRes = R.string.core_ui_type_fairy,
        backgroundColor = Color(0xFFF48FB1),
        textColor = Color.Black,
    ),
    ;

    companion object {
        fun fromApi(name: String): PokemonType =
            entries.firstOrNull { it.apiName == name.lowercase() } ?: NORMAL
    }
}
