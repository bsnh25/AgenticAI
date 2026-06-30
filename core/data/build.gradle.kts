plugins {
    alias(deps.plugins.android.library)
    alias(deps.plugins.kotlin.android)
    alias(deps.plugins.ksp)
    alias(deps.plugins.hilt)
}

android {
    namespace = "com.learnagentic.core.data"
    compileSdk = config.versions.compileSdk.get().toInt()
    defaultConfig { minSdk = config.versions.minSdk.get().toInt() }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:common"))

    // Hilt
    implementation(deps.libraries.hilt.android)
    ksp(deps.libraries.hilt.android.compiler)

    // Room
    implementation(deps.libraries.room.runtime)
    implementation(deps.libraries.room.ktx)
    ksp(deps.libraries.room.compiler)

    // Retrofit
    implementation(deps.libraries.retrofit)
    implementation(deps.libraries.retrofit.gson)
    implementation(deps.libraries.okhttp)
    implementation(deps.libraries.okhttp.logging)
    implementation(deps.libraries.gson)

    // Chucker (debug only)
    debugImplementation(deps.libraries.chucker)
    releaseImplementation(deps.libraries.chucker.no.op)

    implementation(deps.libraries.coroutines.android)
    implementation(deps.libraries.core.ktx)
}
