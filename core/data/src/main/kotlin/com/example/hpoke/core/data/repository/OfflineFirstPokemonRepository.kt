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

package com.example.hpoke.core.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.hpoke.core.data.mapper.asModel
import com.example.hpoke.core.data.mediator.PokemonRemoteMediator
import com.example.hpoke.core.database.dao.PokemonDao
import com.example.hpoke.core.database.model.PokemonFull
import com.example.hpoke.core.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class OfflineFirstPokemonRepository :
    PokemonRepository,
    KoinComponent {
    val pokemonDao: PokemonDao by inject()
    val pokemonRemoteMediator: PokemonRemoteMediator by inject()

    @OptIn(ExperimentalPagingApi::class)
    override fun getPokemons(pageSize: Int): Flow<PagingData<Pokemon>> =
        Pager(
            config = PagingConfig(pageSize = pageSize),
            remoteMediator = pokemonRemoteMediator,
            pagingSourceFactory = pokemonDao::getAllPokemon,
        )
            .flow
            .map(PagingData<PokemonFull>::asModel)

    override fun getPokemon(id: Int): Flow<Pokemon> =
        pokemonDao.getPokemonById(id = id)
            .map(PokemonFull::asModel)
}
