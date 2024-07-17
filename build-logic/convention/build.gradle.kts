import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

// TODO : need to understand deeply
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "resy.android.application"
            implementationClass = "plugins.AndroidAppPlugin"
        }
        register("androidAppCompose") {
            id = "resy.android.application.compose"
            implementationClass = "plugins.AndroidAppComposePlugin"
        }

        register("androidLibrary") {
            id = "resy.android.library"
            implementationClass = "plugins.AndroidLibraryPlugin"
        }

        register("androidLibCompose") {
            id = "resy.android.library.compose"
            implementationClass = "plugins.AndroidLibComposePlugin"
        }
    }
}