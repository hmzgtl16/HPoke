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
            apply(
                plugin = libs.findPlugin("org-jetbrains-kotlin-plugin-serialization").get()
                    .get().pluginId
            )

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
                    project(":core:navigation")
                )
                add(
                    "implementation",
                    project(":core:ui")
                )
                add(
                    "implementation",
                    platform(libs.findLibrary("com-github-skydoves-landscapist-bom").get())
                )
                add(
                    "implementation",
                    libs.findLibrary("androidx-navigation3-runtime").get()
                )
                add(
                    "implementation",
                    libs.findLibrary("org-jetbrains-kotlinx-serialization-core").get()
                )
                add(
                    "implementation",
                    libs.findLibrary("io-insert-koin-androidx-compose").get()
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