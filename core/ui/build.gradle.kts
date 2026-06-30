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
    kotlin { compilerOptions { jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17) } }
    buildFeatures { compose = true }
}

dependencies {
    api(platform(deps.compose.bom))
    api(deps.compose.ui)
    api(deps.compose.ui.graphics)
    api(deps.compose.ui.tooling.preview)
    api(deps.compose.material3)
    api(deps.compose.material.icons)
    debugImplementation(deps.compose.ui.tooling)
}
