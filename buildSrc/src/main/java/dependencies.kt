import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.add
import org.gradle.kotlin.dsl.exclude


object Versions {

    // Network
    const val RETROFIT = "2.9.0"
    const val OKHTTP = "5.0.0-alpha.2"
    const val GSON = "2.9.0"

    // Coroutines
    const val COROUTINES_CORE = "1.7.3"
    const val COROUTINES_ANDROID = "1.7.3"
    const val LIFECYCLE = "2.7.0"

    // Hilt
    const val HILT = "2.51.1"
    const val KAPT_HILT_COMPILER = "1.2.0"

    // Glide
    const val GLIDE = "4.16.0"

    // Other
    const val CHUCKER = "4.0.0"
    const val NAVIGATION = "2.7.7"
    const val FRAGMENT = "1.6.2"
    const val ACTIVITY = "1.8.2"
    const val RUNTIME_WORKER_KTX = "2.7.0"
    const val RUNTIME_WORKER = "2.7.1"


    const val ROOM = "2.6.1"


}
fun DependencyHandler.testingDependencies(){

    add("androidTestImplementation", Libs.Testing.CORE_TESTING)
    add("testImplementation", Libs.Testing.CORE_TESTING)
    add("testImplementation", Libs.Testing.COROUTINES)
    add("testImplementation", Libs.Testing.MOCKK)
    add("testImplementation", Libs.Testing.MOCKK_ANDROID)
    add("testImplementation", Libs.Testing.MOCKK_AGENT)

}
fun DependencyHandler.roomDependencies() {
    add("implementation", Libs.ROOM.RUNTIME)
    add("annotationProcessor", Libs.ROOM.ANNOTATION_PROCESSOR)
    add("kapt", Libs.ROOM.KAPT)

    add("implementation", Libs.ROOM.COROUTINES)
    add("testImplementation", Libs.ROOM.TEST)
}


fun DependencyHandler.hiltDependencies() {
    add("implementation", Libs.Hilt.HILT_ANDROID)
    add("implementation", Libs.Hilt.HILT_WORKER)


    add("kapt", Libs.Hilt.KAPT_HILT_DAGGER_COMPILER)
    add("kapt", Libs.Hilt.KAPT_HILT_ANDROID_COMPILER)
    add("kapt", Libs.Hilt.KAPT_HILT_COMPILER)

    // to fix Targeting S+ (version 31 and above) requires that one of FLAG_IMMUTABLE or FLAG_MUTABLE be specified when creating a PendingIntent.
    add("implementation", Libs.RUNTIME_WORKER_KTX)
    add("implementation", Libs.RUNTIME_WORKER)
}


fun DependencyHandler.lifecycleDependencies() {
    add("implementation", Libs.Lifecycle.RUNTIME)
    add("implementation", Libs.Lifecycle.VIEW_MODEL)
    add("implementation", Libs.Lifecycle.LIVEDATA)
}


fun DependencyHandler.imagesDependencies() {
    add("implementation", Libs.GLIDE)
}

fun DependencyHandler.coroutinesDependencies() {
    add("implementation", Libs.Coroutines.ANDROID)
    add("implementation", Libs.Coroutines.CORE)
}

fun DependencyHandler.sizeDependencies() {
    add("implementation", Libs.Size.SDP)
    add("implementation", Libs.Size.SSP)
}


fun DependencyHandler.retrofitDependencies() {
    add("implementation", Libs.Retrofit.RETROFIT2) {
        exclude(group = "android.net.http")
        exclude(module = "okhttp")
        exclude(group = "org.apache.httpcomponents", module = "httpclient")
    }
    add("implementation", Libs.Retrofit.CONVERTER_GSON)
    add("implementation", Libs.Retrofit.LOGGING_INTERCEPTOR)
    add("implementation", Libs.Retrofit.GSON)
    add("implementation", Libs.Retrofit.OKHTTP)

}


fun DependencyHandler.navigationDependencies() {
    add("implementation", Libs.Navigation.FRAGMENT)
    add("implementation", Libs.Navigation.FRAGMENT_UI)
}

fun DependencyHandler.chuckerDependencies() {
    add("debugImplementation", Libs.Chucker.DEBUG)
    add("releaseImplementation", Libs.Chucker.RELEASE)
}


fun DependencyHandler.fragmentAndActivityDependencies() {
    add("debugImplementation", Libs.FRAGMENT)
    add("releaseImplementation", Libs.ACTIVITY)
}




