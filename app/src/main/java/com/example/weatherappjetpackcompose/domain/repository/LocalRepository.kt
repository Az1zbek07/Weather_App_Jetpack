package com.example.weatherappjetpackcompose.domain.repository

import com.example.weatherappjetpackcompose.domain.model.CurrentWeather
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun saveWeather(currentWeather: CurrentWeather)
    suspend fun deleteWeather(id: Int)
    fun getWeatherById(id: Int): Flow<CurrentWeather?>
    fun getWeatherList(): Flow<List<CurrentWeather>>

    suspend fun saveTheme(index: Int)
    fun getTheme(): Flow<Int>
}