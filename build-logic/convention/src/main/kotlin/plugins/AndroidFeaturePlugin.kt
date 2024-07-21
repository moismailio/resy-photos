package plugins

import extensions.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeaturePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("resy.android.library")
                apply("resy.android.library.compose")
                apply("resy.android.hilt")
            }
            dependencies {
                add("implementation", project(":design-system"))
                add("implementation", project(":models"))
                add("implementation", project(":core:ui"))
                add("testImplementation", versionCatalog().findBundle("android.test.bundle").get())
            }
        }
    }
}