package com.example.weatherappjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.weatherappjetpackcompose.presentation.screen.RootNavigation
import com.example.weatherappjetpackcompose.presentation.settings.SettingViewModel
import com.example.weatherappjetpackcompose.presentation.theme.WeatherCleanTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val settingViewModel: SettingViewModel = hiltViewModel()
            val state by settingViewModel.themeState.collectAsState()
            val index = when(state) {
                0 -> isSystemInDarkTheme()
                1 -> false
                else -> true
            }
            WeatherCleanTheme(
                darkTheme = index
            ) {
                val navController = rememberNavController()
                RootNavigation(navHostController = navController)
            }
        }
    }
}