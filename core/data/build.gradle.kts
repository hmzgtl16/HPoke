plugins {
    alias(libs.plugins.hpoke.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.serialization)
}

android {
    namespace = "com.example.hpoke.core.data"
}

dependencies {
    api(projects.core.model)
    api(projects.core.database)
    api(projects.core.network)

    implementation(libs.org.jetbrains.kotlinx.coroutines.core)
    implementation(libs.io.insert.koin.core)
}