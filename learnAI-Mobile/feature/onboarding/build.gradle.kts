plugins {
    alias(deps.plugins.android.library)
    alias(deps.plugins.kotlin.android)
    alias(deps.plugins.compose.compiler)
    alias(deps.plugins.ksp)
    alias(deps.plugins.hilt)
}

android {
    namespace = "com.learnagentic.feature.onboarding"
    compileSdk = config.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = config.versions.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:common"))
    implementation(project(":core:data"))

    // Compose
    implementation(platform(deps.compose.bom))
    implementation(deps.compose.ui)
    implementation(deps.compose.ui.graphics)
    implementation(deps.compose.ui.tooling.preview)
    implementation(deps.compose.material3)

    // Lifecycle
    implementation(deps.lifecycle.runtime.ktx)
    implementation(deps.lifecycle.viewmodel.compose)

    // Hilt
    implementation(deps.hilt.android)
    implementation(deps.hilt.navigation.compose)
    ksp(deps.hilt.android.compiler)
}
