plugins {
    id("resy.android.library")
    id("resy.android.hilt")
    id("resy.android.retrofit")
}

android {
    namespace = "com.resy.photo_list_data"
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":photo-list:domain"))
}