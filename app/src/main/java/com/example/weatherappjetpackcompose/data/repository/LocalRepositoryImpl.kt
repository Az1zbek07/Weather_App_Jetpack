package com.example.weatherappjetpackcompose.data.repository

import com.example.weatherappjetpackcompose.data.database.WeatherDao
import com.example.weatherappjetpackcompose.data.manager.DataStoreManager
import com.example.weatherappjetpackcompose.domain.model.CurrentWeather
import com.example.weatherappjetpackcompose.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val dao: WeatherDao,
    private val dataStoreManager: DataStoreManager
): LocalRepository {
    override suspend fun saveWeather(currentWeather: CurrentWeather) {
        dao.saveWeather(currentWeather)
    }

    override suspend fun deleteWeather(id: Int) {
        dao.deleteById(id)
    }

    override fun getWeatherById(id: Int): Flow<CurrentWeather?> {
        return dao.getWeatherId(id)
    }

    override fun getWeatherList(): Flow<List<CurrentWeather>> {
        return dao.getAllFavoriteWeathers()
    }

    override suspend fun saveTheme(index: Int) {
        dataStoreManager.saveTheme(index)
    }

    override fun getTheme(): Flow<Int> {
        return dataStoreManager.getTheme()
    }
}