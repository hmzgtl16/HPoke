plugins {
    alias(libs.plugins.hpoke.android.feature)
    alias(libs.plugins.hpoke.android.library.compose)
}

android {
    namespace = "com.example.hpoke.feature.home"
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.paging.compose)

    // Testing dependencies
    testImplementation(projects.core.testing)
}