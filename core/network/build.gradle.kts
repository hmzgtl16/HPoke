import java.util.Properties

plugins {
    alias(libs.plugins.hpoke.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.serialization)
}

android {
    namespace = "com.example.hpoke.core.network"

    buildFeatures.buildConfig = true

    defaultConfig {
        val properties = Properties()
        val localPropertiesFile = project.rootProject.file("local.properties")
        if (localPropertiesFile.exists()) {
            properties.load(localPropertiesFile.reader())
        }

        buildConfigField("String", "BASE_URL", "\"${properties.getProperty("BASE_URL")}\"")
    }
}

dependencies {
    implementation(libs.io.ktor.client.okhttp)
    implementation(libs.io.ktor.client.content.negotiation)
    implementation(libs.io.ktor.client.logging)
    implementation(libs.io.ktor.serialization.kotlinx.json)
    implementation(libs.ch.qos.logback.classic)
    implementation(libs.io.insert.koin.core)
}