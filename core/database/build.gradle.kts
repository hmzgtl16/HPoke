plugins {
    alias(libs.plugins.hpoke.android.library)
    alias(libs.plugins.hpoke.android.room)
}

android {
    namespace = "com.example.hpoke.core.database"
}

dependencies {

    // Testing
    testImplementation(libs.junit)
    testImplementation(libs.org.jetbrains.kotlinx.coroutines.test)

    // Android Testing (Instrumented Tests)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.org.jetbrains.kotlinx.coroutines.test)
}