package com.learnagentic.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learnagentic.core.common.navigation.Screen
import com.learnagentic.core.data.preferences.OnboardingPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val onboardingPreferences: OnboardingPreferences
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _startDestination = MutableStateFlow<String>(Screen.Onboarding.route)
    val startDestination: StateFlow<String> = _startDestination.asStateFlow()

    init {
        viewModelScope.launch {
            onboardingPreferences.hasCompletedOnboarding.collectLatest { hasCompleted ->
                if (hasCompleted) {
                    _startDestination.value = Screen.Home.route
                } else {
                    _startDestination.value = Screen.Onboarding.route
                }
                _isLoading.value = false
            }
        }
    }
}
