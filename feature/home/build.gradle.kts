plugins {
    alias(libs.plugins.hpoke.android.feature)
    alias(libs.plugins.hpoke.android.library.compose)
}

android {
    namespace = "com.example.hpoke.feature.home"
}

dependencies {
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.paging.compose)

    testImplementation(projects.core.testing)

    androidTestImplementation(projects.core.testing)
    androidTestImplementation(libs.androidx.paging.testing)
}