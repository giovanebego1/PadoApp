plugins {
    id("com.android.application")
}

android {
    namespace = "com.proj.padoapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.proj.padoapp"
        minSdk = 29
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8

    }
    buildToolsVersion = "34.0.0"
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.airbnb.android:lottie:6.1.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.1")
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")
    implementation("org.springframework.boot:spring-boot-dependencies:3.1.5")
    implementation ("com.journeyapps:zxing-android-embedded:4.3.0")
    implementation("androidx.biometric:biometric:1.1.0")
    implementation("androidx.activity:activity:1.6.0-alpha05")
    implementation("com.google.mlkit:common:18.10.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


}