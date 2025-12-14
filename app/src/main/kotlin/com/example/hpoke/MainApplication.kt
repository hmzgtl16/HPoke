package com.example.hpoke

import android.app.Application
import com.example.hpoke.core.data.di.dataModule
import com.example.hpoke.core.database.di.daoModule
import com.example.hpoke.core.database.di.databaseModule
import com.example.hpoke.core.navigation.di.navigationModule
import com.example.hpoke.core.network.di.networkModule
import com.example.hpoke.core.sync.di.syncModule
import com.example.hpoke.core.sync.initializer.Sync
import com.example.hpoke.feature.home.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(androidContext = this@MainApplication)
            workManagerFactory()
            modules(
                dataModule,
                databaseModule,
                daoModule,
                navigationModule,
                networkModule,
                syncModule,
                homeModule
            )
        }

        Sync.initialize(context = this)
    }
}