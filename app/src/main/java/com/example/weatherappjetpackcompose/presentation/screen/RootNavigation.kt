package com.example.weatherappjetpackcompose.presentation.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherappjetpackcompose.presentation.main.MainScreen
import com.example.weatherappjetpackcompose.util.Graph

@Composable
fun RootNavigation(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Graph.Main,
        route = Graph.Root
    ) {
        composable(route =  Graph.Main) {
            MainScreen()
        }
    }
}