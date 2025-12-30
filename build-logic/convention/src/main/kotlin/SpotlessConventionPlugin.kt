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
                    ktlint().editorConfigOverride(
                        mapOf(
                            "indent_size" to "4",
                            "continuation_indent_size" to "4",
                            "ktlint_function_naming_ignore_when_annotated_with" to "Composable"
                        )
                    )
                    trimTrailingWhitespace()
                    endWithNewline()
                }
                kotlinGradle {
                    target("**/*.gradle.kts")
                    targetExclude(layout.buildDirectory.asFileTree)
                    licenseHeaderFile(
                        rootProject.file("spotless/copyright.kts"),
                        "(^(?![\\/ ]\\*).*$)"
                    )
                    trimTrailingWhitespace()
                    endWithNewline()
                }
                format("xml") {
                    target("**/*.xml")
                    targetExclude(layout.buildDirectory.asFileTree)
                    licenseHeaderFile(
                        rootProject.file("spotless/copyright.xml"),
                        "(<[^!?])"
                    )
                    trimTrailingWhitespace()
                    endWithNewline()
                }
            }
        }
    }
}

