import com.android.build.gradle.LibraryExtension
import com.example.hpoke.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = libs.findPlugin("hpoke-android-library").get().get().pluginId)

            extensions.configure<LibraryExtension> {
                testOptions.animationsDisabled = true
            }

            dependencies {
                add(
                    "implementation",
                    project(":core:data")
                )
                add(
                    "implementation",
                    project(":core:designsystem")
                )
                add(
                    "implementation",
                    project(":core:model")
                )
                add(
                    "implementation",
                    project(":core:navigation")
                )
                add(
                    "implementation",
                    project(":core:ui")
                )
                add(
                    "implementation",
                    libs.findLibrary("androidx-compose-material3-adaptive-navigation3").get()
                )
                add(
                    "implementation",
                    libs.findLibrary("com-github-skydoves-landscapist-palette").get()
                )
                add(
                    "androidTestImplementation",
                    libs.findLibrary("androidx-compose-ui-test-junit4").get()
                )
                add(
                    "androidTestImplementation",
                    libs.findLibrary("androidx-compose-ui-test-manifest").get()
                )
            }
        }
    }
}