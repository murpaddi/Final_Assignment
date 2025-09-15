DOWNLOAD FROM THE MASTER BRANCH

An Android App that allows users to view a list of books retrieved from an API. Assembled with MVVM, Retrofit, and Hilt.

Features:
- User login details and error handling
- fetches a book list from an API
- RecyclerView to display books on the dashboard
- Navigation to details screen with book descriptions
- Dependency injection with Hilt
- Coroutines for asynchronous data handling and StateFlow.

Architecture:
- MVVM
- Fragments (Login, Dashboard, Details)
- Repositories for data access
- ViewModels injected via Hilt

DEPENDENCIES - PROJECT LEVEL

// Navigation
    id("androidx.navigation.safeargs.kotlin") version "2.7.7" apply false

// Dependency Injection
    id("com.google.dagger.hilt.android") version "2.51.1" apply false


DEPENDENCIES - APP LEVEL

//Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.8.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.8.3")

// Dependency Injection
    implementation("com.google.dagger:hilt-android:2.51.1")
    implementation("androidx.hilt:hilt-navigation-fragment:1.2.0")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")

// Networking
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.11.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

// Lifecycle / stateflow
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")

PLUGINS - APP LEVEL

    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
    id ("kotlin-kapt")
    id ("com.google.dagger.hilt.android")

ANDROID - APP LEVEL

    kapt {
        correctErrorTypes = true
    }




ASSEMBLY:
Paste dependencies into the corresponding gradle files.
Sync gradle to download dependencies.
Run the app on the emulator.


