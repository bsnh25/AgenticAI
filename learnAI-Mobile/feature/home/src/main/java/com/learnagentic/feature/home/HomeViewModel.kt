package com.learnagentic.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learnagentic.core.domain.model.DifficultyLevel
import com.learnagentic.core.domain.model.LearningModule
import com.learnagentic.core.domain.usecase.GetModulesByLevelUseCase
import com.learnagentic.core.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getModulesByLevelUseCase: GetModulesByLevelUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        fetchFeaturedModules()
    }

    private fun fetchFeaturedModules() {
        getModulesByLevelUseCase(DifficultyLevel.EASY).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _uiState.value = HomeUiState.Loading
                }
                is Resource.Success -> {
                    val modules = result.data ?: emptyList()
                    _uiState.value = HomeUiState.Success(
                        greeting = "Ready to learn?",
                        progressPercent = 0.45f,
                        featuredModules = modules,
                        continueModule = modules.firstOrNull()
                    )
                }
                is Resource.Error -> {
                    _uiState.value = HomeUiState.Failed(result.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}

sealed interface HomeUiState {
    object Loading : HomeUiState
    object Initial : HomeUiState
    data class Success(
        val greeting: String,
        val progressPercent: Float,
        val featuredModules: List<LearningModule>,
        val continueModule: LearningModule?
    ) : HomeUiState
    data class Failed(val message: String) : HomeUiState
}
