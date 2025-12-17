package com.example.hpoke.core.data.repository

import androidx.paging.PagingData
import com.example.hpoke.core.data.sync.Syncable
import com.example.hpoke.core.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository : Syncable {

    fun getPokemons(pageSize: Int = 10): Flow<PagingData<Pokemon>>

    suspend fun getPokemon(id: Int): Flow<Pokemon?>
}