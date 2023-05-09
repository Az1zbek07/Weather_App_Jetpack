package com.example.weatherappjetpackcompose.domain.repository

import com.example.weatherappjetpackcompose.domain.model.CurrentWeather
import kotlinx.coroutines.flow.Flow

interface NetworkRepository {
    suspend fun getCurrentWeather(query: String): Flow<CurrentWeather>
}