package plugins

import com.android.build.api.dsl.ApplicationExtension
import configs.ApplicationConfig
import extensions.configureAndroidKotlin
import extensions.configureBuildTypes
import extensions.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class AndroidAppPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("kotlin-android")
                apply("kotlin-parcelize")
                apply("resy.android.hilt")
            }

            extensions.configure<ApplicationExtension>() {
                defaultConfig.apply {
                    targetSdk = ApplicationConfig.targetSdkVersion
                    applicationId = ApplicationConfig.applicationId
                    versionCode = ApplicationConfig.versionCode
                    versionName = ApplicationConfig.versionName
                }
                configureAndroidKotlin(this)
                configureBuildTypes(this)
            }

            dependencies {
                add("implementation", project(":design-system"))
                add("implementation", project(":core:ui"))
                add("implementation", project(":models"))
                add("implementation", project(":photo-details:presentation"))
                add("implementation", project(":photo-list:presentation"))
                add("implementation",versionCatalog().findLibrary("coil").get())
                add("testImplementation", versionCatalog().findBundle("android.test.bundle").get())
            }
        }
    }
}



