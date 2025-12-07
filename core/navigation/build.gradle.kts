plugins {
    alias(libs.plugins.hpoke.android.library)
}

android {
    namespace = "com.example.hpoke.core.navigation"
}

dependencies {
    api(libs.androidx.navigation3.runtime)
    api(libs.io.insert.koin.core)
    api(libs.io.insert.koin.compose.navigation3)
}