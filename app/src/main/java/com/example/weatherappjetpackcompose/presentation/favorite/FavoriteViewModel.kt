package com.example.weatherappjetpackcompose.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappjetpackcompose.domain.use_case.all.AllUseCases
import com.example.weatherappjetpackcompose.presentation.favorite.FavoriteEvent
import com.example.weatherappjetpackcompose.presentation.favorite.FavoriteState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val allUseCases: AllUseCases
) : ViewModel() {
    private val _state: MutableStateFlow<FavoriteState> = MutableStateFlow(FavoriteState())
    val state = _state.asStateFlow()

    init {
        readFavoriteWeathers()
    }

    fun onEvent(event: FavoriteEvent) {
        when(event) {
            is FavoriteEvent.OnWeatherDelete -> {
                viewModelScope.launch(Dispatchers.IO) {
                    allUseCases.deleteWeatherByIdUseCase(event.id)
                }
            }
        }
    }

    private fun readFavoriteWeathers() {
        viewModelScope.launch(Dispatchers.IO) {
            allUseCases.getAllFavoriteWeathersUseCase(Unit)
                .onStart {
                    _state.update {
                        it.copy(isLoading = true)
                    }
                    delay(200L)
                }
                .collectLatest { list ->
                    _state.update {
                        it.copy(weatherList = list, isLoading = false)
                    }
                }
        }
    }
}