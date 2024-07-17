package extensions

import com.android.build.api.dsl.CommonExtension
import configs.ApplicationConfig
import configs.JVMConfig
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.Properties

fun Project.configureAndroidKotlin(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    with(commonExtension) {
        namespace = ApplicationConfig.nameSpace
        compileSdk = ApplicationConfig.compileSdkVersion
        buildFeatures.buildConfig = true

        defaultConfig.apply {
            minSdk = ApplicationConfig.minSdkVersion
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            vectorDrawables.useSupportLibrary = true

            val properties = Properties()
            properties.load(project.rootProject.file("local.properties").inputStream())
            buildConfigField("String", "HOST_URL", properties.getProperty("HOST_URL"))
        }

        compileOptions {
            sourceCompatibility = JVMConfig.javaVersion
            targetCompatibility = JVMConfig.javaVersion
        }

        packaging.resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"

        dependencies {
            add("implementation", versionCatalog().findLibrary("androidx-core-ktx").get())
            add("implementation", versionCatalog().findLibrary("androidx-appcompat").get())
            add(
                "implementation",
                versionCatalog().findLibrary("androidx-lifecycle-runtime-ktx").get()
            )
            add("implementation", versionCatalog().findLibrary("material").get())

            add("testImplementation", versionCatalog().findLibrary("junit").get())
            add("androidTestImplementation", versionCatalog().findLibrary("androidx-junit").get())
            add(
                "androidTestImplementation",
                versionCatalog().findLibrary("androidx-espresso-core").get()
            )
        }

        tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions {
                jvmTarget = JVMConfig.kotlinJvm
                freeCompilerArgs = freeCompilerArgs + JVMConfig.freeCompilerArgs
            }
        }
    }
}