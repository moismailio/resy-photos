package plugins

import extensions.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class RetrofitPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            dependencies {
                add("implementation",versionCatalog().findLibrary("retrofit").get())
                add("implementation",versionCatalog().findLibrary("converter.gson").get())
                add("implementation",versionCatalog().findLibrary("okhttp").get())
                add("implementation",versionCatalog().findLibrary("logging.interceptor").get())
            }
        }
    }
}