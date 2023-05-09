package com.example.weatherappjetpackcompose.data.remote

import com.example.weatherappjetpackcompose.data.model.CurrentWeatherDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("/data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("q") query: String,
        @Query("units")units:  String = "standard",
        @Query("appid") key: String = "454c1775fdd4ab4d77aa9a26e4e72f16"
    ): Response<CurrentWeatherDTO>
}