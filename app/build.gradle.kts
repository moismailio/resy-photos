plugins {
    id("resy.android.application")
    id("resy.android.application.compose")
    id("resy.android.retrofit")
}

dependencies {
    implementation(project(":design-system"))
    implementation(project(":core:ui"))
    implementation(project(":models"))
    implementation(project(":photo-details:presentation"))
    implementation(project(":photo-list:presentation"))
}