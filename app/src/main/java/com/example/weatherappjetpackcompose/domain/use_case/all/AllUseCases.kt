package com.example.weatherappjetpackcompose.domain.use_case.all

import com.example.weatherappjetpackcompose.domain.use_case.local.DeleteWeatherByIdUseCase
import com.example.weatherappjetpackcompose.domain.use_case.local.GetAllFavoriteWeathersUseCase
import com.example.weatherappjetpackcompose.domain.use_case.local.GetThemeUseCase
import com.example.weatherappjetpackcompose.domain.use_case.local.GetWeatherByIdUseCase
import com.example.weatherappjetpackcompose.domain.use_case.local.SaveFavoriteWeatherUseCase
import com.example.weatherappjetpackcompose.domain.use_case.local.SaveThemeUseCase
import com.example.weatherappjetpackcompose.domain.use_case.remote.GetCurrentWeatherUseCase

data class AllUseCases(
    val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    val saveFavoriteWeatherUseCase: SaveFavoriteWeatherUseCase,
    val getWeatherByIdUseCase: GetWeatherByIdUseCase,
    val deleteWeatherByIdUseCase: DeleteWeatherByIdUseCase,
    val getAllFavoriteWeathersUseCase: GetAllFavoriteWeathersUseCase,
    val saveThemeUseCase: SaveThemeUseCase,
    val getThemeUseCase: GetThemeUseCase
)