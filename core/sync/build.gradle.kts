plugins {
    alias(libs.plugins.hpoke.android.library)
}

android {
    namespace = "com.example.hpoke.core.sync"
}

dependencies {
    implementation(projects.core.data)
    implementation(libs.androidx.work.runtime)
    implementation(libs.io.insert.koin.core)
    api(libs.io.insert.koin.androidx.workmanager)
    implementation(libs.org.jetbrains.kotlinx.coroutines.core)
}