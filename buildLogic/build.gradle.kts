plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    compileOnly(libs.plugins.android.application.map { it.let { d -> "com.android.application:com.android.application.gradle.plugin:${d.version}" } }.let { "" }.let { "" })
    implementation(libs.gradle.android)
    implementation(libs.gradle.kotlin)
    implementation(libs.gradle.ksp)
}

kotlin {
    jvmToolchain(17)
}
