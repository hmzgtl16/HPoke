plugins {
    alias(libs.plugins.hpoke.android.library)
}

android {
    namespace = "com.example.hpoke.core.data"
}

dependencies {
    implementation(projects.core.database)
    implementation(projects.core.model)
    implementation(projects.core.network)

    implementation(libs.androidx.paging.runtime)
}