package com.learnagentic.feature.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * HomeViewModel — manages Home screen state.
 * TODO (Frontend Agent, S1-04): Wire to GetModulesByLevelUseCase.
 */
@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState
}

sealed interface HomeUiState {
    object Loading : HomeUiState
    object Initial : HomeUiState
    data class Success(val greeting: String) : HomeUiState
    data class Failed(val message: String) : HomeUiState
}
