plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kotlinAndroidKsp)
    alias(libs.plugins.hiltAndroid)
}

android {
    compileSdk = 34

    defaultConfig {
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    flavorDimensions += "environment"

    productFlavors {
        create("development") {
            dimension = "environment"
            namespace = "dev.pankaj.cleanarchitecture.dev"
            applicationId = "dev.pankaj.cleanarchitecture.dev"
            buildConfigField("String", "BASE_URL", "\"https://fakestoreapi.com\"")
            buildConfigField("String", "APP_VERSION", "\"$versionName-dev")
            buildConfigField("String", "FIREBASE_CONFIG_FILE", "\"google-services-dev.json\"")
        }

        create("production") {
            dimension = "environment"
            namespace = "dev.pankaj.cleanarchitecture"
            applicationId = "dev.pankaj.cleanarchitecture"
            buildConfigField("String", "BASE_URL", "\"https://production.fakestoreapi.com\"")
            buildConfigField("String", "APP_VERSION", "$versionName")
            buildConfigField("String", "FIREBASE_CONFIG_FILE", "\"google-services.json\"")
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.annotation)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(libs.glide)
    ksp(libs.glideKsp)


    implementation(libs.androidx.room.runtime)
    ksp(libs.room.compiler)

    implementation(libs.androidx.datastore.preferences)
    implementation(platform(libs.firebase.bom))
    implementation(libs.converter.gson)
    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.okhttp.urlconnection)
    implementation(libs.logging.interceptor)
    implementation(libs.gson)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}