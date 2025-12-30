/*
 * Copyright (C) 2025 Hamza Gattal
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.hpoke

import android.app.Application
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.request.allowHardware
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.example.hpoke.core.data.di.dataModule
import com.example.hpoke.core.database.di.daoModule
import com.example.hpoke.core.database.di.databaseModule
import com.example.hpoke.core.navigation.di.navigationModule
import com.example.hpoke.core.network.BuildConfig
import com.example.hpoke.core.network.di.networkModule
import com.example.hpoke.feature.details.di.detailsModule
import com.example.hpoke.feature.home.di.homeModule
import okio.Path.Companion.toOkioPath
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import java.io.File

class MainApplication :
    Application(),
    SingletonImageLoader.Factory {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(androidContext = this@MainApplication)
            modules(
                dataModule,
                databaseModule,
                daoModule,
                navigationModule,
                networkModule,
                homeModule,
                detailsModule,
            )
        }
    }

    override fun newImageLoader(context: PlatformContext): ImageLoader =
        ImageLoader
            .Builder(context = context)
            .memoryCache(
                initializer = {
                    MemoryCache
                        .Builder()
                        .maxSizePercent(context = context, percent = 0.25)
                        .build()
                },
            )
            .diskCache(
                initializer = {
                    DiskCache
                        .Builder()
                        .directory(
                            directory = File(context.cacheDir, "image_cache").toOkioPath(),
                        ).maxSizePercent(percent = 0.02)
                        .build()
                },
            )
            .allowHardware(enable = false)
            .crossfade(enable = true)
            .apply {
                if (BuildConfig.DEBUG) {
                    logger(logger = DebugLogger())
                }
            }.build()
}
