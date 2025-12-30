plugins {
    alias(libs.plugins.hpoke.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.serialization)
}

android {
    namespace = "com.example.hpoke.core.navigation"
}

dependencies {
    api(libs.androidx.navigation3.runtime)
    api(libs.androidx.navigation3.ui)
    implementation(libs.org.jetbrains.kotlinx.serialization.core)
}