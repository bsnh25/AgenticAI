plugins {
    alias(deps.plugins.android.application)
    alias(deps.plugins.kotlin.android)
    alias(deps.plugins.ksp)
    alias(deps.plugins.hilt)
    alias(deps.plugins.compose.compiler)
}

android {
    namespace = "com.learnagentic.app"
    compileSdk = config.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.learnagentic.app"
        minSdk = config.versions.minSdk.get().toInt()
        targetSdk = config.versions.targetSdk.get().toInt()
        versionCode = config.versions.versionCode.get().toInt()
        versionName = config.versions.versionName.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true

        // Inject API base URL into BuildConfig per flavor
        buildConfigField("String", "API_BASE_URL", "\"${project.findProperty("API_BASE_URL") ?: "http://10.0.2.2:8080/"}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
        }
    }

    flavorDimensions += "environment"
    productFlavors {
        create("dev") {
            dimension = "environment"
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            buildConfigField("String", "API_BASE_URL", "\"http://10.0.2.2:8080/\"")
        }
        create("prod") {
            dimension = "environment"
            buildConfigField("String", "API_BASE_URL", "\"https://api.learnagentic.com/\"")
        }
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
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Modules
    implementation(project(":core:ui"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:common"))
    implementation(project(":feature:home"))
    implementation(project(":feature:explore"))
    implementation(project(":feature:reader"))
    implementation(project(":feature:glossary"))
    implementation(project(":feature:profile"))

    // Core
    implementation(deps.core.ktx)
    implementation(deps.activity.compose)

    // Compose BOM
    implementation(platform(deps.compose.bom))
    implementation(deps.compose.ui)
    implementation(deps.compose.ui.graphics)
    implementation(deps.compose.ui.tooling.preview)
    implementation(deps.compose.material3)

    // Hilt
    implementation(deps.hilt.android)
    ksp(deps.hilt.android.compiler)

    // Navigation
    implementation(deps.navigation.compose)
    implementation(deps.hilt.navigation.compose)

    // Lifecycle
    implementation(deps.lifecycle.runtime.ktx)
    implementation(deps.lifecycle.viewmodel.compose)
    implementation(deps.lifecycle.runtime.compose)

    // Debug tools
    debugImplementation(deps.compose.ui.tooling)
    debugImplementation(deps.compose.ui.test.manifest)

    // Test
    testImplementation(deps.junit)
    androidTestImplementation(deps.junit.android)
    androidTestImplementation(deps.espresso)
    androidTestImplementation(platform(deps.compose.bom))
    androidTestImplementation(deps.compose.ui.test.junit4)
}
