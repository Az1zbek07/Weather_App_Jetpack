package com.example.weatherappjetpackcompose.domain.use_case.local

import com.example.weatherappjetpackcompose.domain.repository.LocalRepository
import com.example.weatherappjetpackcompose.domain.use_case.all.BaseUseCase
import javax.inject.Inject

typealias SaveThemeBaseUseCase = BaseUseCase<Int, Unit>

class SaveThemeUseCase @Inject constructor(
    private val repository: LocalRepository
): SaveThemeBaseUseCase {
    override suspend fun invoke(parameter: Int) {
        repository.saveTheme(parameter)
    }
}