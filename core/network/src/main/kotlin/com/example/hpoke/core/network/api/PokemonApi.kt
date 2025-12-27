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