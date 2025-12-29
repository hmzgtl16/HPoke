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

class OfflineFirstPokemonRepository : PokemonRepository, KoinComponent {

    val pokemonDao: PokemonDao by inject()
    val pokemonRemoteMediator: PokemonRemoteMediator by inject()

    @OptIn(ExperimentalPagingApi::class)
    override fun getPokemons(pageSize: Int): Flow<PagingData<Pokemon>> =
        Pager(
            config = PagingConfig(pageSize = pageSize),
            remoteMediator = pokemonRemoteMediator,
            pagingSourceFactory = pokemonDao::getAllPokemon
        )
            .flow
            .map(PagingData<PokemonFull>::asModel)

    override fun getPokemon(id: Int): Flow<Pokemon> =
        pokemonDao.getPokemonById(id = id)
            .map(PokemonFull::asModel)
}
