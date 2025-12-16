package com.example.hpoke.core.sync.initializer

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager
import com.example.hpoke.core.sync.worker.SyncWorker

object Sync {

    fun initialize(context: Context) {
        WorkManager.getInstance(context = context).apply {
            enqueueUniquePeriodicWork(
                uniqueWorkName = SyncWorker.SYNC_WORK_NAME,
                existingPeriodicWorkPolicy = ExistingPeriodicWorkPolicy.KEEP,
                request = SyncWorker.startUpSyncWork(),
            )
        }
    }
}