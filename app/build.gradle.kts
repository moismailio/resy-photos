plugins {
    id("resy.android.application")
    id("resy.android.application.compose")
}

dependencies {

    // hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    kapt(libs.androidx.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    implementation(libs.log.timber)
    implementation(libs.coil)

    testImplementation("io.mockk:mockk-android:1.13.5")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
//    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.9.22")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
//    api("io.mockk:mockk-android:1.13.5")
//    implementation(project(":design-system"))

    implementation(project(":design-system"))

}

kapt {
    correctErrorTypes = true
}
