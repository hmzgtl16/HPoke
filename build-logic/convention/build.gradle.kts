import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
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
