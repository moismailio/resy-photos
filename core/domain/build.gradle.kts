plugins {
    id("resy.android.library")
}

android {
    namespace = "com.resy.domain"
}

dependencies {
    api(project(":models"))
}