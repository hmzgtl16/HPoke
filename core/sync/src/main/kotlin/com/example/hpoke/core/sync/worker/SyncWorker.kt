package com.example.hpoke.core.sync.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkerParameters
import com.example.hpoke.core.data.repository.PokemonRepository
import com.example.hpoke.core.sync.initializer.SyncConstraints
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.concurrent.TimeUnit

class SyncWorker(
    appContext: Context,
    workerParams: WorkerParameters,
) : CoroutineWorker(appContext = appContext, params = workerParams), KoinComponent {

    private val pokemonRepository: PokemonRepository by inject()

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val syncedSuccessfully = pokemonRepository.sync()

        if (syncedSuccessfully)
            Result.success()
        else
            Result.retry()
    }

    companion object {

        const val SYNC_WORK_NAME = "SyncWorkName"

        fun startUpSyncWork() = PeriodicWorkRequestBuilder<SyncWorker>(
            repeatInterval = 1,
            repeatIntervalTimeUnit = TimeUnit.DAYS
        )
            //.setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .setConstraints(SyncConstraints)
            .build()
    }
}