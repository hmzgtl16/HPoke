package com.example.hpoke

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureKoin(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {

    commonExtension.apply {

        dependencies {
            val bom = libs.findLibrary("io-insert-koin-bom").get()
            add("implementation", platform(bom))
            add("implementation", libs.findLibrary("io-insert-koin-core").get())
            add("implementation", libs.findLibrary("io-insert-koin-androidx-compose").get())
            add(
                "implementation",
                libs.findLibrary("io-insert-koin-androidx-compose-navigation3").get()
            )
            add("testImplementation", platform(bom))
            add("testImplementation", libs.findLibrary("io-insert-koin-test").get())
        }
    }
}