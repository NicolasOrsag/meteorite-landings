package com.example.meteoritelandings.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.meteoritelandings.presentation.meteorite_list.MeteoriteListScreen
import com.example.meteoritelandings.presentation.theme.MeteoriteLandingsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeteoriteLandingsTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "meteorite_list_screen"){
                    composable("meteorite_list_screen"){
                        MeteoriteListScreen(navController = navController)
                    }
                }
            }
        }
    }
}

