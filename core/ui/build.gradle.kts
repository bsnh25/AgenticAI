plugins {
    alias(deps.plugins.android.library)
    alias(deps.plugins.kotlin.android)
    alias(deps.plugins.ksp)
    alias(deps.plugins.compose.compiler)
}

android {
    namespace = "com.learnagentic.core.ui"
    compileSdk = config.versions.compileSdk.get().toInt()
    defaultConfig { minSdk = config.versions.minSdk.get().toInt() }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }
    buildFeatures { compose = true }
}

dependencies {
    api(platform(deps.libraries.compose.bom))
    api(deps.libraries.compose.ui)
    api(deps.libraries.compose.ui.graphics)
    api(deps.libraries.compose.ui.tooling.preview)
    api(deps.libraries.compose.material3)
    api(deps.libraries.compose.material.icons)
    debugImplementation(deps.libraries.compose.ui.tooling)
}
