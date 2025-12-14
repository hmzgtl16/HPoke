package com.example.hpoke.core.sync.di

import com.example.hpoke.core.sync.status.SyncManager
import com.example.hpoke.core.sync.status.SyncManagerImpl
import com.example.hpoke.core.sync.worker.SyncWorker
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val syncModule = module {
    singleOf(::SyncWorker)
    singleOf(::SyncManagerImpl) { bind<SyncManager>() }
}