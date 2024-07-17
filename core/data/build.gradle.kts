plugins {
    id("resy.android.library")
    id("resy.android.hilt")
}

android {
    namespace = "com.resy.data"
}

dependencies {
    api(project(":core:domain"))

    // add it as a bundle
    api(libs.retrofit)
    api(libs.converter.gson)
    api(libs.okhttp)
    api(libs.logging.interceptor)

    api(libs.log.timber)

    api(project(":models"))
}