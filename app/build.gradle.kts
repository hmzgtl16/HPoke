import com.example.hpoke.BuildType

plugins {
    alias(libs.plugins.hpoke.android.application)
    alias(libs.plugins.hpoke.android.application.compose)
}

android {
    namespace = "com.example.hpoke"

    defaultConfig {
        applicationId = "com.example.hpoke"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            applicationIdSuffix = BuildType.DEBUG.applicationIdSuffix
        }
        release {
            isMinifyEnabled = true
            applicationIdSuffix = BuildType.RELEASE.applicationIdSuffix
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
            excludes.add("/META-INF/INDEX.LIST")
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.database)
    implementation(projects.core.designsystem)
    implementation(projects.core.navigation)
    implementation(projects.core.network)
    implementation(projects.feature.home)
    implementation(projects.feature.details)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)
    implementation(libs.com.github.skydoves.landscapist.coil)
}