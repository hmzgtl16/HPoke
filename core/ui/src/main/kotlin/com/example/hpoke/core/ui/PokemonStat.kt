package com.example.hpoke.core.ui

import androidx.annotation.StringRes
import com.example.hpoke.core.model.Stat

enum class PokemonStat(
    val apiName: String,
    @StringRes val labelRes: Int,
    val max: Int = 255
) {
    HP(
        apiName = "hp",
        labelRes = R.string.core_ui_stat_hp
    ),
    ATTACK(
        apiName = "attack",
        labelRes = R.string.core_ui_stat_attack
    ),
    DEFENSE(
        apiName = "defense",
        labelRes = R.string.core_ui_stat_defense
    ),
    SPECIAL_ATTACK(
        apiName = "special-attack",
        labelRes = R.string.core_ui_stat_special_attack
    ),
    SPECIAL_DEFENSE(
        apiName = "special-defense",
        labelRes = R.string.core_ui_stat_special_defense
    ),
    SPEED(
        apiName = "speed",
        labelRes = R.string.core_ui_stat_speed
    );
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