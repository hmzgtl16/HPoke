plugins {
    alias(libs.plugins.hpoke.android.library)
}

android {
    namespace = "com.example.hpoke.core.testing"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.model)
    implementation(libs.androidx.paging.common)
    implementation(libs.junit)

    implementation(libs.org.jetbrains.kotlinx.coroutines.test)
}