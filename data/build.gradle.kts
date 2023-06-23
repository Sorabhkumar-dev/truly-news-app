plugins {
    id("com.android.library")
    id("kotlinx-serialization")
    id("org.jetbrains.kotlin.plugin.serialization")
    kotlin("android")

}

android {
    namespace = "com.soarbh.data"
    compileSdk = 33

    defaultConfig {
        minSdk = 23
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)

    //ktor
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.serialization)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.content.negotiation)

    //kotlin serializer
    implementation(libs.kotlinx.serialization.json)

    //koin
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)

    //data-store
    implementation(libs.datastore.preferences.core)
    implementation(libs.datastore.preferences)
}