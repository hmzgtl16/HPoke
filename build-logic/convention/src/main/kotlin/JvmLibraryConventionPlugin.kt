import com.example.hpoke.configureKotlinJvm
import com.example.hpoke.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class JvmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = libs.findPlugin("org-jetbrains-kotlin-jvm").get().get().pluginId)

            configureKotlinJvm()
        }
    }
}