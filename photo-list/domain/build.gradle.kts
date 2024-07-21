plugins {
    id("resy.android.library")
    id("resy.android.hilt")
}

android {
    namespace = "com.resy.photo_list_domain"
}

dependencies {
    api(project(":core:domain"))
}