plugins {
    alias(deps.plugins.android.library)
    alias(deps.plugins.kotlin.android)
    alias(deps.plugins.ksp)
    alias(deps.plugins.hilt)
    alias(deps.plugins.compose.compiler)
}

android {
    namespace = "com.learnagentic.feature.glossary"
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
    implementation(project(":core:ui"))
    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation(project(":core:common"))
    implementation(deps.hilt.android)
    ksp(deps.hilt.android.compiler)
    implementation(deps.hilt.navigation.compose)
    implementation(deps.lifecycle.viewmodel.compose)
    implementation(deps.lifecycle.runtime.compose)
    implementation(deps.navigation.compose)
}
