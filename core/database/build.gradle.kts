plugins {
    alias(libs.plugins.hpoke.android.library)
    alias(libs.plugins.hpoke.android.room)
}

android {
    namespace = "com.example.hpoke.core.database"
}

dependencies {
    implementation(libs.org.jetbrains.kotlinx.datetime)
}