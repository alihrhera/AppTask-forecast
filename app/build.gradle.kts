plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")


}

android {
    namespace = AppConfiguration.NAMESPACE
    compileSdk = AppConfiguration.COMPILE_SDK

    defaultConfig {
        applicationId = AppConfiguration.APPLICATION_ID
        minSdk = AppConfiguration.MIN_SDK
        targetSdk = AppConfiguration.TARGET_SDK
        versionCode = AppConfiguration.VERSION_CODE
        versionName = AppConfiguration.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "FORECAST_BASE_URL", "\"https://dev-orcas.s3.eu-west-1.amazonaws.com/uploads/\"")
            buildConfigField("String", "CITIES_BASE_URL", "\"https://dev-orcas.s3.eu-west-1.amazonaws.com/uploads/\"")

        }
        debug {
            buildConfigField("String", "FORECAST_BASE_URL", "\"https://api.openweathermap.org/data/2.5/\"")
            buildConfigField("String", "CITIES_BASE_URL", "\"https://dev-orcas.s3.eu-west-1.amazonaws.com/uploads/\"")
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
        dataBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    roomDependencies()

    hiltDependencies()

    lifecycleDependencies()

    navigationDependencies()

    retrofitDependencies()
    // for testing purposes to show api responses
    chuckerDependencies()
}