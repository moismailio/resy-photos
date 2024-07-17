plugins {
    id("resy.android.application")
    id("resy.android.application.compose")
    id("resy.android.retrofit")
}

dependencies {
    implementation(libs.coil)

    implementation(project(":design-system"))
    implementation(project(":models"))

    implementation(project(":photo-details:presentation"))
    implementation(project(":photo-list:presentation"))
}