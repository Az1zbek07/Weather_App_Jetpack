package com.example.weatherappjetpackcompose.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappjetpackcompose.domain.use_case.all.AllUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val allUseCases: AllUseCases
): ViewModel() {
    var themeState = MutableStateFlow(0)
        private set

    init {
        viewModelScope.launch {
            allUseCases.getThemeUseCase(Unit).collect {
                themeState.value = it
            }
        }
    }

    fun saveTheme(index: Int) {
        viewModelScope.launch {
            allUseCases.saveThemeUseCase(index)
        }
    }
}