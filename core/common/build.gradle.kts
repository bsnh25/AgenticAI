plugins {
    alias(deps.plugins.android.library)
    alias(deps.plugins.kotlin.android)
    alias(deps.plugins.ksp)
    
}

android {
    namespace = "com.learnagentic.core.common"
    compileSdk = config.versions.compileSdk.get().toInt()
    defaultConfig { minSdk = config.versions.minSdk.get().toInt() }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }
}

dependencies {
    implementation(deps.core.ktx)
    implementation(deps.coroutines.android)
}
