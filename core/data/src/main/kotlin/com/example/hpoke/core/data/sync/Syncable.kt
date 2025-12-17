package com.example.hpoke.core.data.sync

interface Syncable {
    suspend fun sync(): Boolean
}