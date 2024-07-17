package plugins

import com.android.build.api.dsl.LibraryExtension
import extensions.configureAndroidKotlin
import extensions.configureBuildTypes
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply("com.android.library")
                apply("kotlin-android")
            }
            extensions.configure<LibraryExtension>{
                configureAndroidKotlin(this)
                configureBuildTypes(this)
            }
        }
    }
}