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
    implementation(deps.hilt.android)
    ksp(deps.hilt.android.compiler)

    // Room
    implementation(deps.room.runtime)
    implementation(deps.room.ktx)
    ksp(deps.room.compiler)

    // Retrofit
    implementation(deps.retrofit)
    implementation(deps.retrofit.gson)
    implementation(deps.okhttp)
    implementation(deps.okhttp.logging)
    implementation(deps.gson)

    // Chucker (debug only)
    debugImplementation(deps.chucker)
    releaseImplementation(deps.chucker.no.op)

    implementation(deps.coroutines.android)
    implementation(deps.core.ktx)
}
