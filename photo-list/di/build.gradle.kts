plugins {
    id("resy.android.library")
    id("resy.android.hilt")
    id("resy.android.retrofit")
}

android {
    namespace = "com.resy.photo_list_di"
}

dependencies {
    implementation(project(":photo-list:data"))
    implementation(project(":photo-list:domain"))
}