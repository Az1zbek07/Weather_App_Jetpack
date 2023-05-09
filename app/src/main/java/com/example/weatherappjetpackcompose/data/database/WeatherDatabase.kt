package com.example.weatherappjetpackcompose.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherappjetpackcompose.domain.model.CurrentWeather

@Database(entities = [CurrentWeather::class], version = 1, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase() {
    abstract val dao: WeatherDao
}