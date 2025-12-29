package com.example.hpoke.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.hpoke.core.database.model.RemoteKeyEntity

@Dao
interface RemoteKeyDao {
    @Query("SELECT * FROM remote_key WHERE pokemonId = :id")
    suspend fun remoteKeyPokemonId(id: Int): RemoteKeyEntity?

    @Upsert
    suspend fun insertAll(keys: List<RemoteKeyEntity>)

    @Query("DELETE FROM remote_key")
    suspend fun clearRemoteKeys()
}