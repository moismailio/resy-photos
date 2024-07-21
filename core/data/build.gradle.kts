plugins {
    id("resy.android.library")
    id("resy.android.hilt")
    id("resy.android.retrofit")
}

android {
    namespace = "com.resy.data"
}

dependencies {
    api(project(":core:domain"))
    api(project(":models"))
}