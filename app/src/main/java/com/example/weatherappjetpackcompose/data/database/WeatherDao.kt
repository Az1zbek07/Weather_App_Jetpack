package com.example.weatherappjetpackcompose.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherappjetpackcompose.domain.model.CurrentWeather
import kotlinx.coroutines.flow.Flow

@Dao // Data Access object
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveWeather(currentWeather: CurrentWeather)

    @Query("DELETE FROM CurrentWeather WHERE id = :weatherId")
    suspend fun deleteById(weatherId: Int)

    @Query("SELECT * FROM CurrentWeather WHERE id = :weatherId")
    fun getWeatherId(weatherId: Int): Flow<CurrentWeather?>

    @Query("SELECT * FROM CurrentWeather ORDER BY roomId DESC")
    fun getAllFavoriteWeathers(): Flow<List<CurrentWeather>>
}