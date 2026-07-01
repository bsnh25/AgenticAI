package com.learnagentic.core.common.navigation

/**
 * Type-safe navigation route definitions.
 * All screen routes are defined here as a single source of truth.
 */
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Explore : Screen("explore")
    object Glossary : Screen("glossary")
    data object Onboarding : Screen("onboarding")
    object Profile : Screen("profile")
    object ModuleDetail : Screen("module/{moduleId}") {
        fun createRoute(id: String) = "module/$id"
    }
    object Quiz : Screen("quiz/{moduleId}") {
        fun createRoute(id: String) = "quiz/$id"
    }
    object GlossaryDetail : Screen("glossary/{termId}") {
        fun createRoute(id: String) = "glossary/$id"
    }
}
