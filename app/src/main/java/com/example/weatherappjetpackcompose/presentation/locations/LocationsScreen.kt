package com.example.weatherappjetpackcompose.presentation.locations

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weatherappjetpackcompose.R
import com.google.accompanist.coil.rememberCoilPainter
import com.example.weatherappjetpackcompose.presentation.component.AppAnimation
import com.example.weatherappjetpackcompose.util.Constants
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun LocationsScreen(
    state: LocationScreenState
) {
    val currentTime by remember {
        mutableStateOf(SimpleDateFormat("EEE, MMM dd", Locale.getDefault()).format(Date()))
    }

    if (state.isLoading) {
        AppAnimation(id = R.raw.loading)
    }

    state.success?.let { weather ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = weather.name,
                style = MaterialTheme.typography.displayMedium
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = currentTime,
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val painter = rememberCoilPainter(request = Constants.getImageUrl(weather.icon))
                Image(painter = painter, contentDescription = "", modifier = Modifier.size(100.dp))
                Text(
                    text = Constants.calculateCelsius(weather.temp),
                    style = MaterialTheme.typography.displayMedium
                )
                Text(
                    text = "℃",
                    style = MaterialTheme.typography.displayMedium
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = weather.title,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(40.dp))
            Divider(
                thickness = 2.dp,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(80.dp))
            WeatherItem(title = "Wind ${weather.wind} m/s", icon = R.drawable.wind)
            WeatherItem(title = "Humidity ${weather.humidity}%", icon = R.drawable.humidity)
            WeatherItem(title = "Pressure ${weather.pressure} hpa", icon = R.drawable.pressure)
        }
    }
}

@Composable
fun WeatherItem(
    title: String,
    icon: Int
) {
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "",
            modifier = Modifier.size(40.dp),
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary)
        )
        Spacer(modifier = Modifier.width(15.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium
        )
    }
}