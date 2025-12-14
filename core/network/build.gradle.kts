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
    implementation(libs.io.ktor.client.android)
    implementation(libs.io.ktor.client.content.negotiation)
    implementation(libs.io.ktor.client.logging)
    implementation(libs.io.ktor.serialization.kotlinx.json)
    implementation(libs.com.squareup.okhttp3.logging.interceptor)
    implementation(libs.org.slf4j.android)
    implementation(libs.io.insert.koin.core)

    // Test dependencies
    testImplementation(libs.junit)
    testImplementation(libs.org.jetbrains.kotlin.test)
    testImplementation(libs.org.jetbrains.kotlinx.coroutines.test)
    testImplementation(libs.io.ktor.client.mock)
    testImplementation(libs.io.insert.koin.test)
}