package com.example.weatherappjetpackcompose.presentation.locations

import com.example.weatherappjetpackcompose.domain.model.CurrentWeather

data class LocationScreenState(
    val isLoading: Boolean = false,
    val message: String = "",
    val success: CurrentWeather? = null,
    val isLiked: Boolean = false
)