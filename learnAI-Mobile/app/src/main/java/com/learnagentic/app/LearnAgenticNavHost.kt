package com.learnagentic.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.learnagentic.core.common.navigation.Screen

/**
 * Root NavHost for all screens.
 * Each feature screen is wired here; ViewModels are injected by Hilt.
 */
@Composable
fun LearnAgenticNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            com.learnagentic.feature.home.HomeScreen(
                onModuleClick = { moduleId -> 
                    navController.navigate(Screen.ModuleDetail.createRoute(moduleId))
                }
            )
        }
        composable(
            route = Screen.ModuleDetail.route,
        ) { backStackEntry ->
            val moduleId = backStackEntry.arguments?.getString("moduleId") ?: ""
            // Temporary placeholder until ModuleDetail feature is implemented
            androidx.compose.material3.Text("Module Detail Screen for: $moduleId")
        }
        composable(Screen.Explore.route) {
            // ExploreScreen injected from :feature:explore
        }
        composable(Screen.Glossary.route) {
            // GlossaryScreen injected from :feature:glossary
        }
        composable(Screen.Profile.route) {
            // ProfileScreen injected from :feature:profile
        }
    }
}
