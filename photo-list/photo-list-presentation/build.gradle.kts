plugins {
    id("resy.android.feature")
}

android {
    namespace = "com.resy.photo_list_presentation"
}

dependencies {
    implementation(project(":photo-list:photo-list-domain"))
}