package com.example.hpoke.core.sync.initializer

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.hpoke.core.sync.worker.SyncWorker

object Sync {

    fun initialize(context: Context) {
        WorkManager.getInstance(context).apply {
            enqueueUniqueWork(
                uniqueWorkName = SyncWorker.SYNC_WORK_NAME,
                existingWorkPolicy = ExistingWorkPolicy.KEEP,
                request = SyncWorker.startUpSyncWork(),
            )
        }
    }
}