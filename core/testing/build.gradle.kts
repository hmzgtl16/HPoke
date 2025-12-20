plugins {
    alias(libs.plugins.hpoke.android.library)
}

android {
    namespace = "com.example.hpoke.core.testing"
}

dependencies {
    implementation(projects.core.data)
    implementation(libs.androidx.paging.common)
    api(libs.androidx.paging.testing)
    implementation(libs.junit)

    api(libs.org.jetbrains.kotlinx.coroutines.test)
}