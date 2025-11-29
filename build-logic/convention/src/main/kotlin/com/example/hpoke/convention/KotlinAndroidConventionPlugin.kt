package com.example.hpoke.convention

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class KotlinAndroidConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.android")

            extensions.configure<AppExtension> {
                kotlinOptions {
                    jvmTarget = "11"
                }
            }
        }
    }
}

