package com.example.meteoritelandings.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.meteoritelandings.presentation.meteorite_detail.MeteoriteDetailScreen
import com.example.meteoritelandings.presentation.meteorite_list.MeteoriteListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = Screen.MeteoriteList.route
    ) {
        composable(route = Screen.MeteoriteList.route) {
            MeteoriteListScreen(navController = navController)
        }
        composable(
            route = Screen.MeteoriteDetail.route + "/{name}",
            arguments = listOf(navArgument("name") {
                type = NavType.StringType
            })
        ) {
            MeteoriteDetailScreen(name = it.arguments?.getString("name"), navController = navController)
        }
    }
}