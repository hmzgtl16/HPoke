plugins {
    alias(libs.plugins.hpoke.android.library)
    alias(libs.plugins.hpoke.android.library.compose)
}

android {
    namespace = "com.example.hpoke.core.ui"
}

dependencies {
    implementation(projects.core.designsystem)
    implementation(projects.core.model)
    implementation(libs.com.github.skydoves.landscapist.palette)
}
