plugins {
}
    }
        }
            implementationClass = "com.example.hpoke.convention.KotlinAndroidConventionPlugin"
            id = "com.example.hpoke.kotlin.android"
        register("kotlinAndroid") {
        }
            implementationClass = "com.example.hpoke.convention.AndroidComposeConventionPlugin"
            id = "com.example.hpoke.android.compose"
        register("androidCompose") {
        }
            implementationClass = "com.example.hpoke.convention.AndroidApplicationConventionPlugin"
            id = "com.example.hpoke.android.application"
        register("androidApplication") {
    plugins {
gradlePlugin {

}
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.android.gradlePlugin)
dependencies {

}
    `kotlin-dsl`

