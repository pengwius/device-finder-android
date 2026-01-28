plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android) version "2.2.20"
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "pengwius.devicefinder"
    compileSdk = 36

    defaultConfig {
        applicationId = "pengwius.devicefinder"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation("io.insert-koin:koin-android:3.4.0")
    implementation("io.insert-koin:koin-androidx-compose:3.4.0")
    implementation(libs.androidx.appcompat)
    implementation(libs.hilt.android)
    implementation(libs.material3)

    implementation("androidx.compose.ui:ui:1.10.1")
    implementation("androidx.compose.ui:ui-graphics:1.10.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.10.1")
    implementation("androidx.compose.runtime:runtime:1.10.1")
    implementation("androidx.compose.animation:animation:1.10.1")
    implementation("androidx.compose.foundation:foundation:1.10.1")
    implementation("androidx.compose.material3:material3:1.5.0-alpha10")
    implementation("androidx.compose.material:material-icons-extended:1.7.0")

    implementation("androidx.core:core-ktx:1.16.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.4")
    implementation("androidx.datastore:datastore-preferences:1.2.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.3.0")
    implementation("androidx.benchmark:benchmark-traceprocessor:1.4.1")

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.10.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    debugImplementation(libs.androidx.ui.test.manifest)
    debugImplementation("androidx.compose.ui:ui-tooling:1.10.1")
}