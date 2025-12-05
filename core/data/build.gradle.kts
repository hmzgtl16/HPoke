plugins {
    alias(libs.plugins.hpoke.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.serialization)
}

android {
    namespace = "com.example.hpoke.core.data"
}

dependencies {
    api(projects.core.database)
    api(projects.core.network)
}