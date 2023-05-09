package com.example.weatherappjetpackcompose.presentation.locations

import com.example.weatherappjetpackcompose.domain.model.CurrentWeather

sealed class LocationEvent {
    data class OnSearched(val query: String): LocationEvent()
    data class OnUpdateWeather(val currentWeather: CurrentWeather): LocationEvent()
}