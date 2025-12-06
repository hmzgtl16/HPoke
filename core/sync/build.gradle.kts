plugins {
    alias(libs.plugins.hpoke.android.library)
}

android {
    namespace = "com.example.hpoke.core.sync"
}

dependencies {
    implementation(libs.org.jetbrains.kotlinx.coroutines.core)
    implementation(libs.io.insert.koin.core)
    implementation(libs.io.insert.koin.androidx.workmanager)
}