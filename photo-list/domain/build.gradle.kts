plugins {
    id("resy.android.library")
    // TODO : find a way to share from core
    id("resy.android.hilt")
}

android {
    namespace = "com.resy.photo_list_domain"
}

dependencies {
    api(project(":core:domain"))
}