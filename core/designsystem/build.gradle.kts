plugins {
    alias(libs.plugins.hpoke.android.library)
    alias(libs.plugins.hpoke.android.library.compose)
}

android {
    namespace = "com.example.hpoke.core.designsystem"
}

dependencies {
    api(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.androidx.compose.ui.text.google.fonts)
    implementation(libs.io.coil.compose)
    implementation(libs.com.github.skydoves.landscapist.coil)
    implementation(libs.com.github.skydoves.landscapist.palette)
    implementation(libs.com.github.skydoves.landscapist.placeholder)
}