package plugins

import com.android.build.api.dsl.ApplicationExtension
import configs.ApplicationConfig
import extensions.configureAndroidKotlin
import extensions.configureBuildTypes
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidAppPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("kotlin-android")
                apply("kotlin-kapt")
                apply("com.google.dagger.hilt.android")
                apply("kotlin-parcelize")
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
        }
    }
}



