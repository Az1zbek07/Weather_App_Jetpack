package com.example.weatherappjetpackcompose.domain.use_case.local

import com.example.weatherappjetpackcompose.domain.repository.LocalRepository
import com.example.weatherappjetpackcompose.domain.use_case.all.BaseUseCase
import javax.inject.Inject

typealias DeleteWeatherByIdBaseUseCase = BaseUseCase<Int, Unit>

class DeleteWeatherByIdUseCase @Inject constructor(
    private val localRepository: LocalRepository
): DeleteWeatherByIdBaseUseCase {
    override suspend fun invoke(parameter: Int) {
        localRepository.deleteWeather(parameter)
    }
}