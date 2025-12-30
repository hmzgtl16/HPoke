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
import com.example.hpoke.core.model.Stat

enum class PokemonStat(
    val apiName: String,
    @StringRes val labelRes: Int,
    val max: Int = 255,
) {
    HP(
        apiName = "hp",
        labelRes = R.string.core_ui_stat_hp,
    ),
    ATTACK(
        apiName = "attack",
        labelRes = R.string.core_ui_stat_attack,
    ),
    DEFENSE(
        apiName = "defense",
        labelRes = R.string.core_ui_stat_defense,
    ),
    SPECIAL_ATTACK(
        apiName = "special-attack",
        labelRes = R.string.core_ui_stat_special_attack,
    ),
    SPECIAL_DEFENSE(
        apiName = "special-defense",
        labelRes = R.string.core_ui_stat_special_defense,
    ),
    SPEED(
        apiName = "speed",
        labelRes = R.string.core_ui_stat_speed,
    ),
}

fun List<Stat>.asStatMap(): Map<PokemonStat, Int> =
    buildMap {
        for (stat in this@asStatMap) {
            val type = fromApiName(stat.name.lowercase()) ?: continue
            put(type, stat.baseStat)
        }
    }

private fun fromApiName(name: String): PokemonStat? =
    PokemonStat.entries.firstOrNull { it.apiName == name }
