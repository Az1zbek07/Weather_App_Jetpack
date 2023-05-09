package com.example.weatherappjetpackcompose.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CurrentWeather")
data class CurrentWeather(
    var long: Double,
    var lat: Double,
    var title: String,
    var description: String,
    var icon: String,
    var temp: Double,
    var pressure: Int,
    var humidity: Int,
    var name: String,
    var timeZone: Int,
    var wind: Double,
    var country: String,
    var id: Int,
    @PrimaryKey(autoGenerate = true)
    var roomId: Int = 0
) {
    constructor() : this(
        0.0,
        0.0,
        "",
        "",
        "",
        0.0,
        0,
        0,
        "",
        0,
        0.0,
        "",
        0,
        0
    )
}