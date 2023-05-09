package com.example.weatherappjetpackcompose.domain.use_case.local

import com.example.weatherappjetpackcompose.domain.model.CurrentWeather
import com.example.weatherappjetpackcompose.domain.repository.LocalRepository
import com.example.weatherappjetpackcompose.domain.use_case.all.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetWeatherByIdBaseUseCase = BaseUseCase<Int, Flow<CurrentWeather?>>

class GetWeatherByIdUseCase @Inject constructor(
    private val repository: LocalRepository
): GetWeatherByIdBaseUseCase {
    override suspend fun invoke(parameter: Int): Flow<CurrentWeather?> {
        return repository.getWeatherById(parameter)
    }
}