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

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureKoin(
    commonExtension: CommonExtension,
) {
    commonExtension.apply {
        dependencies {
            val bom = libs.findLibrary("io-insert-koin-bom").get()
            add("implementation", platform(bom))
            add("implementation", libs.findLibrary("io-insert-koin-core").get())
            add("implementation", libs.findLibrary("io-insert-koin-androidx-compose").get())
            add(
                "implementation",
                libs.findLibrary("io-insert-koin-androidx-compose-navigation3").get(),
            )
            add("testImplementation", platform(bom))
            add("testImplementation", libs.findLibrary("io-insert-koin-test").get())
        }
    }
}
