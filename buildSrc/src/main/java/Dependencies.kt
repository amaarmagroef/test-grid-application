/**
 * Created by Anwar on 16 Mar 2020.
 */

object Config {
    const val minSdkVersion = 21
    const val targetSdkVersion = 29
    const val compileSdkVersion = 29
    const val buildToolsVersion = "29.0.3"

    const val instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object Versions {
    /**
     * Project Classpath dependencies
     */
    const val gradle = "3.6.1"
    const val navSafeArgs = "2.2.1"
    const val firebaseAppDistGradle = "1.3.1"
    const val playServicesGradle = "4.3.3"
    const val firebaseCrashlyticsGradle = "2.0.0-beta02"

    /**
     * App Dependencies
     *
     * AndroidX
     */
    const val appCompat = "1.1.0"
    const val coreKtx = "1.2.0"
    const val constraint = "1.1.3"
    const val coordinator = "1.1.0"
    const val navigation = "2.2.1"
    const val multidex = "2.0.1"
    const val fragment = "1.2.2"
    const val room = "2.2.5"

    //Arch component
    const val lifecycle = "2.2.0"

    //Kotlin
    const val kotlin = "1.3.70"
    const val coroutinesCore = "1.3.3"

    //Google's
    const val gson = "2.8.6"
    const val material = "1.0.0"
    const val playServices = "17.0.0"
    const val firebaseAnalytics = "17.2.2"
    const val firebaseCrashlytics = "17.0.0-beta01"

    //Media
    const val glide = "4.11.0"

    //Networks
    const val retrofit = "2.7.2"
    const val okhttp = "4.3.1"

    //DI
    const val javaxAnnotation = "1.0"
    const val javaxInject = "1"
    const val dagger = "2.26"
    const val glassFishAnnotation = "10.0-b28"

    //Testing
    const val jUnit = "4.13"
    const val extjUnit = "1.1.1"
    const val espresso = "3.2.0"
    const val archComponent = "2.1.0"

    //rxJava
    const val rxJava = "3.0.0"
}

object GradleClasspath {
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val navSafeArgs =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navSafeArgs}"

    //google's
    const val playServices = "com.google.gms:google-services:${Versions.playServicesGradle}"
    const val firebaseAppDist =
        "com.google.firebase:firebase-appdistribution-gradle:${Versions.firebaseAppDistGradle}"
    const val firebaseCrashlytics =
        "com.google.firebase:firebase-crashlytics-gradle:${Versions.firebaseCrashlyticsGradle}"
}

object AndroidX {
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
    const val constraintLayoutSolver =
        "androidx.constraintlayout:constraintlayout-solver:${Versions.constraint}"
    const val coordinatorLayout =
        "androidx.coordinatorlayout:coordinatorlayout:${Versions.coordinator}"
    const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val multidex = "androidx.multidex:multidex:${Versions.multidex}"
    const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"
}

object Room{
    const val runtime = "androidx.room:room-runtime:${Versions.room}"
    const val compiler = "androidx.room:room-compiler:${Versions.room}"
    const val ktx = "androidx.room:room-ktx:${Versions.room}"
}

object Kotlin {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesCore}"
}

object Lifecycles{
    const val runtime = "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycle}"
    const val extentions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val savedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}"
    const val compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    const val commonJava8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
    const val reactiveStreams = "androidx.lifecycle:lifecycle-reactivestreams-ktx:${Versions.lifecycle}"
    const val services = "androidx.lifecycle:lifecycle-service:${Versions.lifecycle}"
}

object Googles {
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val mapsService = "com.google.android.gms:play-services-maps:${Versions.playServices}"
    const val locationService = "com.google.android.gms:play-services-location:${Versions.playServices}"
    const val firebaseAnalytics =
        "com.google.firebase:firebase-analytics:${Versions.firebaseAnalytics}"
    const val firebaseCrashlytics =
        "com.google.firebase:firebase-crashlytics:${Versions.firebaseCrashlytics}"
}

object Media{
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object RxJava {
    const val rxandroid = "io.reactivex.rxjava3:rxandroid:${Versions.rxJava}"
    const val rxjava = "io.reactivex.rxjava3:rxjava:${Versions.rxJava}"
    const val rxjavaadapter = "com.github.akarnokd:rxjava3-retrofit-adapter:${Versions.rxJava}"
}

object Injections {
    const val javaxAnnotation = "javax.annotation:jsr250-api:${Versions.javaxAnnotation}"
    const val javaxInject = "javax.inject:javax.inject:${Versions.javaxInject}"
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val daggerAndroid = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val daggerProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    const val glassFishAnnotaion = "org.glassfish:javax.annotation:${Versions.glassFishAnnotation}"
}

object Network {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttpLogger = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
}

object Testing {
    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val extjUnit = "androidx.test.ext:junit:${Versions.extjUnit}"
    const val kotlinjUnit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val espressoIntent = "androidx.test.espresso:espresso-intents:${Versions.espresso}"
    const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
    const val archComponent = "androidx.arch.core:core-testing:${Versions.archComponent}"
}