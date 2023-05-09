package com.example.weatherappjetpackcompose.presentation.favorite

import com.example.weatherappjetpackcompose.domain.model.CurrentWeather

data class FavoriteState(
    val isLoading: Boolean = false,
    val weatherList: List<CurrentWeather> = emptyList()
)