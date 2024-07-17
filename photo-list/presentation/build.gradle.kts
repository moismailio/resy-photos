plugins {
    id("resy.android.feature")
}

android {
    namespace = "com.resy.photo_list_presentation"
}

dependencies {
    implementation(project(":photo-list:domain"))
    implementation(project(":photo-list:di"))
}