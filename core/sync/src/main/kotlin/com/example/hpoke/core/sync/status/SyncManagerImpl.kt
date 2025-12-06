package com.example.hpoke.core.sync.status

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.hpoke.core.sync.initializer.anyRunning
import com.example.hpoke.core.sync.worker.SyncWorker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SyncManagerImpl : SyncManager, KoinComponent {

    private val context: Context by inject()

    override val isSyncing: Flow<Boolean> =
        WorkManager.getInstance(context)
            .getWorkInfosForUniqueWorkFlow(SyncWorker.SYNC_WORK_NAME)
            .map(List<WorkInfo>::anyRunning)
            .conflate()

    override fun requestSync() {
        WorkManager.getInstance(context)
            .enqueueUniqueWork(
                uniqueWorkName = SyncWorker.SYNC_WORK_NAME,
                existingWorkPolicy = ExistingWorkPolicy.KEEP,
                request = SyncWorker.startUpSyncWork(),
            )
    }
}