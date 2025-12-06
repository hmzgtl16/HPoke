package com.example.hpoke.core.data.sync

import kotlin.coroutines.cancellation.CancellationException

interface Synchronizer {

    suspend fun sync(): Boolean
}

suspend fun <T> suspendRunCatching(block: suspend () -> T): Result<T> = try {
    Result.success(block())
} catch (cancellationException: CancellationException) {
    throw cancellationException
} catch (exception: Exception) {
    Result.failure(exception)
}