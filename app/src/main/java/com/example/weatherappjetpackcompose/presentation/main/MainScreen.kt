package com.example.weatherappjetpackcompose.presentation.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.weatherappjetpackcompose.util.SearchState
import com.example.weatherappjetpackcompose.presentation.component.BottomBar
import com.example.weatherappjetpackcompose.presentation.component.CustomTopBar
import com.example.weatherappjetpackcompose.presentation.component.SearchTextField
import com.example.weatherappjetpackcompose.presentation.favorite.FavoriteScreen
import com.example.weatherappjetpackcompose.presentation.favorite.FavoriteViewModel
import com.example.weatherappjetpackcompose.presentation.locations.LocationEvent
import com.example.weatherappjetpackcompose.presentation.locations.LocationViewModel
import com.example.weatherappjetpackcompose.presentation.locations.LocationsScreen
import com.example.weatherappjetpackcompose.presentation.screen.BottomBarScreen
import com.example.weatherappjetpackcompose.presentation.settings.SettingScreen
import com.example.weatherappjetpackcompose.util.Graph
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navHostController = rememberNavController()
    val current by navHostController.currentBackStackEntryAsState()
    var searchState by remember {
        mutableStateOf(SearchState.CLOSED)
    }
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val locationViewModel = hiltViewModel<LocationViewModel>()
    val state by locationViewModel.state.collectAsState()
    var isFabVisible by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = Unit) {
        navHostController.currentBackStackEntryFlow.collect {
            if (it.destination.route == "Locations") {
                isFabVisible = true
            } else {
                isFabVisible = false
                searchState = SearchState.CLOSED
            }
        }
    }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        modifier = Modifier.fillMaxSize(),
        topBar = {
            if (searchState == SearchState.CLOSED) {
                CustomTopBar(
                    title = current?.destination?.route.toString(),
                    isSearchIconVisible = current?.destination?.route.toString() == "Locations",
                    onSearchClicked = {
                        searchState = SearchState.OPENED
                    }
                )
            } else {
                SearchTextField(
                    onSearched = {
                        if (it.isBlank()) {
                            scope.launch {
                                snackBarHostState.showSnackbar(
                                    message = "Query must be not empty!"
                                )
                            }
                        } else {
                            locationViewModel.onEvent(LocationEvent.OnSearched(it))
                        }
                    },
                    onClosed = {
                        searchState = SearchState.CLOSED
                    }
                )
            }
        },
        bottomBar = {
            BottomBar(navHostController = navHostController)
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = isFabVisible,
                exit = fadeOut(),
                enter = fadeIn()
            ) {
                FloatingActionButton(
                    onClick = {
                        locationViewModel.onEvent(LocationEvent.OnUpdateWeather(state.success!!))
                    }
                ) {
                    Icon(
                        imageVector = if (state.isLiked) Icons.Default.Favorite else Icons.Outlined.Favorite,
                        contentDescription = "favorite",
                        tint = if (state.isLiked) Color.Red else Color.Black
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            modifier = Modifier.padding(padding),
            navController = navHostController,
            route = Graph.Main,
            startDestination = BottomBarScreen.Locations.route
        ) {
            composable(route = BottomBarScreen.Locations.route) {
                LocationsScreen(
                    state = state
                )
            }
            composable(route = BottomBarScreen.Favorite.route) {
                val favoriteViewModel: FavoriteViewModel = hiltViewModel()
                val favoriteState by favoriteViewModel.state.collectAsState()
                FavoriteScreen(
                    favoriteState,
                    onEvent = {
                        favoriteViewModel.onEvent(it)
                        scope.launch {
                            snackBarHostState.showSnackbar(
                                message = "Deleted"
                            )
                        }
                    }
                )
            }
            composable(route = BottomBarScreen.Settings.route) {
                SettingScreen()
            }
        }
    }
}