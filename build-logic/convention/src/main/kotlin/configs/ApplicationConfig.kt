package configs

import org.gradle.api.JavaVersion

object ApplicationConfig {
    val minSdkVersion : Int = 24
    val targetSdkVersion : Int = 34
    val compileSdkVersion : Int = 34
    val applicationId : String = "com.resy.photos"
    val versionCode : Int = 1
    val versionName : String = "1.0"
    val nameSpace: String = "com.resy.photos"
}

object JVMConfig{
    val javaVersion : JavaVersion = JavaVersion.VERSION_1_8
    val kotlinJvm : String = "1.8"
    val freeCompilerArgs : List<String> = listOf()
}

