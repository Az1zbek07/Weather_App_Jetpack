package com.example.weatherappjetpackcompose.presentation.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherappjetpackcompose.presentation.component.ThemeCheckBox

@Composable
fun SettingScreen() {
    val settingViewModel: SettingViewModel = hiltViewModel()
    val state by settingViewModel.themeState.collectAsState()
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        ThemeCheckBox(
            title = "Automatic",
            isChecked = state == 0,
            onChecked = {
                settingViewModel.saveTheme(0)
            }
        )
        ThemeCheckBox(
            title = "Light",
            isChecked = state == 1,
            onChecked = {
                settingViewModel.saveTheme(1)
            }
        )
        ThemeCheckBox(
            title = "Dark",
            isChecked = state == 2,
            onChecked = {
                settingViewModel.saveTheme(2)
            }
        )
    }
}