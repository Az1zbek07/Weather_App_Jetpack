package com.example.weatherappjetpackcompose.presentation.favorite

sealed interface FavoriteEvent {
    data class OnWeatherDelete(val id: Int): FavoriteEvent
}