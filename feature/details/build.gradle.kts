plugins {
    alias(libs.plugins.hpoke.android.feature)
    alias(libs.plugins.hpoke.android.library.compose)
}

android {
    namespace = "com.example.hpoke.feature.details"
}

dependencies {
    implementation(libs.androidx.compose.material3)

}