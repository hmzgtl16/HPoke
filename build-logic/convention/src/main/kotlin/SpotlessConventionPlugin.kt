/*
 * Copyright (C) 2025 Hamza Gattal
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.diffplug.gradle.spotless.SpotlessExtension
import com.example.hpoke.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class SpotlessConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = libs.findPlugin("com-diffplug-spotless").get().get().pluginId)

            extensions.configure<SpotlessExtension> {
                kotlin {
                    target("**/*.kt")
                    targetExclude(layout.buildDirectory.asFileTree)
                    licenseHeaderFile(rootProject.file("spotless/copyright.kt"))
                    ktlint()
                    trimTrailingWhitespace()
                    endWithNewline()
                }
                kotlinGradle {
                    target("**/*.gradle.kts")
                    targetExclude(layout.buildDirectory.asFileTree)
                    licenseHeaderFile(
                        rootProject.file("spotless/copyright.kts"),
                        "(^(?![\\/ ]\\*).*$)",
                    )
                    trimTrailingWhitespace()
                    endWithNewline()
                }
                format("xml") {
                    target("**/*.xml")
                    targetExclude(layout.buildDirectory.asFileTree)
                    licenseHeaderFile(
                        rootProject.file("spotless/copyright.xml"),
                        "(<[^!?])",
                    )
                    trimTrailingWhitespace()
                    endWithNewline()
                }
            }
        }
    }
}
