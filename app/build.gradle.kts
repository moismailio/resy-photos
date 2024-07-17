plugins {
    id("resy.android.application")
    id("resy.android.application.compose")
}

dependencies {

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)


    implementation(libs.coil)
    implementation(libs.log.timber)


    implementation(project(":design-system"))
    implementation(project(":models"))
    implementation(project(":photo-details"))

}

kapt {
    correctErrorTypes = true
}
