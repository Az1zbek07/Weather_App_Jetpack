package com.example.weatherappjetpackcompose.domain.use_case.local

import com.example.weatherappjetpackcompose.domain.model.CurrentWeather
import com.example.weatherappjetpackcompose.domain.repository.LocalRepository
import com.example.weatherappjetpackcompose.domain.use_case.all.BaseUseCase
import javax.inject.Inject

typealias SaveFavoriteWeatherBaseUseCase = BaseUseCase<CurrentWeather, Unit>

class SaveFavoriteWeatherUseCase @Inject constructor(
    private val repository: LocalRepository
): SaveFavoriteWeatherBaseUseCase {
    override suspend fun invoke(parameter: CurrentWeather) {
        repository.saveWeather(parameter)
    }
}