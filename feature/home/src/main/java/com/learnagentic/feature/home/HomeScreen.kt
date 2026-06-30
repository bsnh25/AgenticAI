package com.learnagentic.feature.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Home screen — placeholder scaffold.
 * TODO (Frontend Agent, S1-04): Implement Home Dashboard with API integration.
 */
@Composable
fun HomeScreen(
    onModuleClick: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "🏠 Home — Sprint 1 Placeholder",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}
