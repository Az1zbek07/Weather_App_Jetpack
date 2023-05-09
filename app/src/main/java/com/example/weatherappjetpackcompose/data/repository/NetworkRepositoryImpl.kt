package com.example.weatherappjetpackcompose.data.repository

import com.example.weatherappjetpackcompose.data.mapper.toCurrentWeather
import com.example.weatherappjetpackcompose.data.remote.WeatherService
import com.example.weatherappjetpackcompose.domain.model.CurrentWeather
import com.example.weatherappjetpackcompose.domain.repository.NetworkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val service: WeatherService
) : NetworkRepository {
    override suspend fun getCurrentWeather(query: String): Flow<CurrentWeather> = flow {
        service.getCurrentWeather(query).body()?.let {
            emit(it.toCurrentWeather())
        }
    }
}