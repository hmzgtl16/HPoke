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
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
    alias(libs.plugins.com.diffplug.spotless)
}

group = "com.example.hpoke.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.androidx.room.gradle.plugin)
    compileOnly(libs.org.jetbrains.kotlin.gradle.plugin)
    compileOnly(libs.com.android.tools.build.gradle.plugin)
    compileOnly(libs.com.diffplug.spotless.gradle.plugin)
    compileOnly(libs.com.google.devtools.ksp.gradle.plugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

// Spotless configuration
spotless {
    kotlin {
        target("**/*.kt")
        targetExclude(layout.buildDirectory.asFileTree)
        licenseHeaderFile(rootProject.file("../spotless/copyright.kt"))
        ktlint().editorConfigOverride(
            mapOf(
                "indent_size" to "4",
                "continuation_indent_size" to "4",
                "ktlint_function_naming_ignore_when_annotated_with" to "Composable",
            ),
        )
        trimTrailingWhitespace()
        endWithNewline()
    }
    kotlinGradle {
        target("**/*.gradle.kts")
        targetExclude(layout.buildDirectory.asFileTree)
        licenseHeaderFile(
            rootProject.file("../spotless/copyright.kts"),
            "(^(?![\\/ ]\\*).*$)",
        )
        trimTrailingWhitespace()
        endWithNewline()
    }
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = libs.plugins.hpoke.android.application.asProvider().get().pluginId
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = libs.plugins.hpoke.android.application.compose.get().pluginId
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = libs.plugins.hpoke.android.library.asProvider().get().pluginId
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = libs.plugins.hpoke.android.library.compose.get().pluginId
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidFeature") {
            id = libs.plugins.hpoke.android.feature.get().pluginId
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidRoom") {
            id = libs.plugins.hpoke.android.room.get().pluginId
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("jvmLibrary") {
            id = libs.plugins.hpoke.jvm.library.get().pluginId
            implementationClass = "JvmLibraryConventionPlugin"
        }
        register("spotless") {
            id = "hpoke.spotless"
            implementationClass = "SpotlessConventionPlugin"
        }
    }
}
