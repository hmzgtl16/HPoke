package com.example.hpoke.core.data.sync

import kotlinx.coroutines.flow.Flow

interface Syncable {

    val isSynced: Flow<Boolean>
    suspend fun sync(): Boolean
}