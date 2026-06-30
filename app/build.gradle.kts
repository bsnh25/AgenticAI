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
    implementation(deps.libraries.core.ktx)
    implementation(deps.libraries.activity.compose)

    // Compose BOM
    implementation(platform(deps.libraries.compose.bom))
    implementation(deps.libraries.compose.ui)
    implementation(deps.libraries.compose.ui.graphics)
    implementation(deps.libraries.compose.ui.tooling.preview)
    implementation(deps.libraries.compose.material3)

    // Hilt
    implementation(deps.libraries.hilt.android)
    ksp(deps.libraries.hilt.android.compiler)

    // Navigation
    implementation(deps.libraries.navigation.compose)
    implementation(deps.libraries.hilt.navigation.compose)

    // Lifecycle
    implementation(deps.libraries.lifecycle.runtime.ktx)
    implementation(deps.libraries.lifecycle.viewmodel.compose)
    implementation(deps.libraries.lifecycle.runtime.compose)

    // Debug tools
    debugImplementation(deps.libraries.compose.ui.tooling)
    debugImplementation(deps.libraries.compose.ui.test.manifest)

    // Test
    testImplementation(deps.libraries.junit)
    androidTestImplementation(deps.libraries.junit.android)
    androidTestImplementation(deps.libraries.espresso)
    androidTestImplementation(platform(deps.libraries.compose.bom))
    androidTestImplementation(deps.libraries.compose.ui.test.junit4)
}
