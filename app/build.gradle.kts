plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.smilesifat.kotlinboilerplate"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.smilesifat.kotlinboilerplate"
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
}

dependencies {

    //noinspection GradleCompatible
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Design Libraries
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation ("androidx.cardview:cardview:1.0.0")
    implementation ("de.hdodenhof:circleimageview:3.1.0")
    implementation ("pl.droidsonroids.gif:android-gif-drawable:1.2.22")
    implementation ("com.airbnb.android:lottie:3.4.0")

    //API Call Retrofit 2
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    //image downloader and controller
//    implementation 'com.squareup.picasso:picasso:2.8'
    implementation ("com.squareup.picasso:picasso:2.71828")
    implementation ("com.github.bumptech.glide:glide:4.11.0")
//
    //jason converter
    implementation ("com.google.code.gson:gson:2.10")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.13.0")

    //shimmer effect
    implementation ("com.facebook.shimmer:shimmer:0.5.0")

    //Moshi Json parser
    implementation ("com.squareup.moshi:moshi:1.12.0")

    //slider dependencies
    implementation ("com.github.denzcoskun:ImageSlideshow:0.1.2")
//    implementation ("com.github.smarteist:autoimageslider:1.4.0")

//     Dagger Hilt
    implementation ("com.google.dagger:hilt-android:2.39.1")
    annotationProcessor ("com.google.dagger:hilt-android-compiler:2.39.1")

//     Hilt-Retrofit Integration
    implementation("com.google.dagger:hilt-android:2.39.1")
    annotationProcessor("com.google.dagger:hilt-android-compiler:2.39.1")
}