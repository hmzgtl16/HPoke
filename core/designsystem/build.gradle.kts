plugins {
    alias(libs.plugins.hpoke.android.library)
    alias(libs.plugins.hpoke.android.library.compose)
}

android {
    namespace = "com.example.hpoke.core.designsystem"
}

dependencies {
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.androidx.compose.ui.text.google.fonts)
    implementation(libs.com.github.skydoves.landscapist.coil)
    implementation(libs.com.github.skydoves.landscapist.palette)
    implementation(libs.com.github.skydoves.landscapist.placeholder)
}