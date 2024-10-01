plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    kotlin("kapt")
}

android {
    namespace = "com.example.peratrack"
    compileSdk = 34

    kapt {
        generateStubs = true
    }

    defaultConfig {
        applicationId = "com.example.peratrack"
        minSdk = 24
        targetSdk = 34
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

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }

    android {
        // Другие настройки...

        packagingOptions {
            resources {
                excludes += "META-INF/DEPENDENCIES"
            }
        }
    }

}

dependencies {
    // Other modules
    implementation(project(":domain"))
    implementation(project(":data"))

    // Google auth
    implementation(libs.play.services.auth) // Google Sign-In для авторизации
    //implementation("com.google.api-client:google-api-client-android:1.33.0") // Клиент для API
    //implementation("com.google.apis:google-api-services-gmail:v1-rev83-1.23.0")

    implementation(libs.kotlin.stdlib)
    implementation(libs.google.api.client)
    implementation(libs.google.oauth.client.jetty)
    implementation(libs.google.api.services.gmail)
    implementation(libs.kotlinx.coroutines.core)

    implementation(libs.guava) // Оставляем только эту версию

    // dagger
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}