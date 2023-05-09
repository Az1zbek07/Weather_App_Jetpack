package com.example.weatherappjetpackcompose.presentation.favorite

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.weatherappjetpackcompose.R
import com.example.weatherappjetpackcompose.presentation.component.AppAnimation
import com.example.weatherappjetpackcompose.presentation.component.FavoriteItem

@Composable
fun FavoriteScreen(
    uiState: FavoriteState,
    onEvent: (FavoriteEvent) -> Unit
) {
    if (uiState.isLoading) {
        AppAnimation(id = R.raw.loading)
    }
    LazyColumn(
        contentPadding = PaddingValues(8.dp)
    ) {
        items(uiState.weatherList, key = {
            it.id
        }) {
            FavoriteItem(currentWeather = it, onDelete = {
                onEvent(FavoriteEvent.OnWeatherDelete(it.id))
            })
        }
    }
}