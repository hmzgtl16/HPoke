import com.android.build.api.dsl.ApplicationExtension
import com.example.hpoke.configureAndroidCompose
import com.example.hpoke.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = libs.findPlugin("com-android-application").get().get().pluginId)
            apply(plugin = libs.findPlugin("org-jetbrains-kotlin-plugin-compose").get().get().pluginId)

            extensions.configure<ApplicationExtension> {
                configureAndroidCompose(commonExtension = this)

                dependencies {
                    add("implementation", libs.findLibrary("androidx-compose-material3").get())
                    add(
                        "implementation",
                        libs.findLibrary("androidx-compose-material3-adaptive-navigation3").get()
                    )
                }
            }
        }
    }
}

