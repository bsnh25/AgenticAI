pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("config") { from(files("gradle/config.versions.toml")) }
        create("deps") { from(files("gradle/dependency.versions.toml")) }
        create("learnai") { from(files("gradle/learnai.versions.toml")) }
    }
}

rootProject.name = "LearnAgenticAI"

include(":app")
include(":core:ui")
include(":core:data")
include(":core:domain")
include(":core:common")
include(":feature:home")
include(":feature:explore")
include(":feature:reader")
include(":feature:glossary")
include(":feature:profile")
include(":feature:onboarding")
