// Top-level build file. Dependency versions are managed via version catalogs.
plugins {
    alias(deps.plugins.android.application) apply false
    alias(deps.plugins.android.library) apply false
    alias(deps.plugins.kotlin.android) apply false
    alias(deps.plugins.kotlin.jvm) apply false
    alias(deps.plugins.hilt) apply false
    alias(deps.plugins.ksp) apply false
    alias(deps.plugins.compose.compiler) apply false
}
