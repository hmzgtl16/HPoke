plugins {
    alias(libs.plugins.hpoke.android.library)
    alias(libs.plugins.hpoke.android.room)
}

android {
    namespace = "com.example.hpoke.core.database"
}

dependencies {
    implementation(libs.org.jetbrains.kotlinx.datetime)
    implementation(libs.io.insert.koin.core)

    // Testing
    testImplementation(libs.junit)
    testImplementation(libs.org.jetbrains.kotlin.test)
    testImplementation(libs.org.jetbrains.kotlinx.coroutines.test)
    testImplementation(libs.io.insert.koin.test)

    // Android Testing (Instrumented Tests)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.org.jetbrains.kotlin.test)
    androidTestImplementation(libs.org.jetbrains.kotlinx.coroutines.test)
    androidTestImplementation(libs.io.insert.koin.test)
}