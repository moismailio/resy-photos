plugins {
    id("resy.android.library")
    // TODO : find a way to share plugin from base
    id("resy.android.hilt")
}

android {
    namespace = "com.resy.photo_list_data"
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":photo-list:photo-list-domain"))
}