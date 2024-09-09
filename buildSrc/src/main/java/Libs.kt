object Libs {


    const val FRAGMENT = "androidx.fragment:fragment-ktx:${Versions.FRAGMENT}"
    const val ACTIVITY = "androidx.activity:activity-ktx:${Versions.ACTIVITY}"
    const val RUNTIME_WORKER_KTX = "androidx.work:work-runtime-ktx:${Versions.RUNTIME_WORKER_KTX}"
    const val RUNTIME_WORKER = "androidx.work:work-runtime:${Versions.RUNTIME_WORKER}"
    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
    // ConstraintLayout
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:2.1.4"


    // Size utilities
    object Size {
        const val SDP = "com.intuit.sdp:sdp-android:1.1.0"
        const val SSP = "com.intuit.ssp:ssp-android:1.1.0"
    }


    // Retrofit
    object Retrofit {
        const val RETROFIT2 = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
        const val LOGGING_INTERCEPTOR =
            "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}"
        const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
        const val CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"
        const val GSON = "com.google.code.gson:gson:${Versions.GSON}"


    }


    // Coroutines
    object Coroutines {
        const val ANDROID =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES_ANDROID}"
        const val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES_CORE}"
    }

    // Hilt
    object Hilt {
        const val HILT_ANDROID = "com.google.dagger:hilt-android:${Versions.HILT}"
        const val KAPT_HILT_DAGGER_COMPILER = "com.google.dagger:hilt-compiler:${Versions.HILT}"
        const val KAPT_HILT_ANDROID_COMPILER =
            "com.google.dagger:hilt-android-compiler:${Versions.HILT}"
        const val KAPT_HILT_COMPILER = "androidx.hilt:hilt-compiler:${Versions.KAPT_HILT_COMPILER}"
        const val HILT_WORKER = "androidx.hilt:hilt-work:1.2.0"
    }

    // chucker for logging
    object Chucker {
        const val DEBUG = "com.github.chuckerteam.chucker:library:${Versions.CHUCKER}"
        const val RELEASE = "com.github.chuckerteam.chucker:library-no-op:${Versions.CHUCKER}"
    }

    // Navigation
    object Navigation {
        const val FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
        const val FRAGMENT_UI = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
    }

    // Lifecycle
    object Lifecycle {
        const val RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE}"
        const val LIVEDATA = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE}"
        const val VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE}"
    }

    object ROOM {
        const val RUNTIME = "androidx.room:room-runtime:${Versions.ROOM}"
        const val ANNOTATION_PROCESSOR = "androidx.room:room-compiler:${Versions.ROOM}"

        // optional - Kotlin Extensions and Coroutines support for Room
        const val COROUTINES = "androidx.room:room-ktx:${Versions.ROOM}"

        const val KAPT = "androidx.room:room-compiler:${Versions.ROOM}"

        // optional - Test helpers
        const val TEST = "androidx.room:room-testing:${Versions.ROOM}"


    }

}
