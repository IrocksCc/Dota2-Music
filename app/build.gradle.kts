plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.dota2.music"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.dota2.music"
        minSdk = 31
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
    kotlinOptions {
        jvmTarget = "1.8"
    }

    dataBinding.enable = true
}

dependencies {

    val appCompat = "1.6.1"
    val latestAndroidIconicsRelease = "5.4.0"
    val superTextViewVersionCode = "2.4.6"

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:${appCompat}")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.mikepenz:iconics-core:${latestAndroidIconicsRelease}")
    implementation("com.mikepenz:iconics-views:${latestAndroidIconicsRelease}")

    implementation("com.github.lygttpod:SuperTextView:${superTextViewVersionCode}")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}