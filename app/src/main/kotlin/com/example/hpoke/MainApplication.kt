package com.example.hpoke

import android.app.Application
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.network.ktor3.KtorNetworkFetcherFactory
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.example.hpoke.core.data.di.dataModule
import com.example.hpoke.core.database.di.daoModule
import com.example.hpoke.core.database.di.databaseModule
import com.example.hpoke.core.navigation.di.navigationModule
import com.example.hpoke.core.network.BuildConfig
import com.example.hpoke.core.network.di.networkModule
import com.example.hpoke.core.sync.di.syncModule
import com.example.hpoke.core.sync.initializer.Sync
import com.example.hpoke.feature.home.di.homeModule
import okio.Path.Companion.toOkioPath
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin
import java.io.File

class MainApplication : Application(), SingletonImageLoader.Factory {

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

    override fun newImageLoader(context: PlatformContext): ImageLoader =
        ImageLoader.Builder(context = context)
            .components { add(factory = KtorNetworkFetcherFactory()) }
            .memoryCache(initializer = {
                MemoryCache.Builder()
                    .maxSizePercent(context = context, percent = 0.25).build()
            })
            .diskCache(initializer = {
                DiskCache.Builder()
                    .directory(
                        directory = File(context.cacheDir, "image_cache").toOkioPath()
                    )
                    .maxSizePercent(percent = 0.02)
                    .build()
            })
            .crossfade(enable = true)
            .apply {
                if (BuildConfig.DEBUG) {
                    logger(logger = DebugLogger())
                }
            }
            .build()
}