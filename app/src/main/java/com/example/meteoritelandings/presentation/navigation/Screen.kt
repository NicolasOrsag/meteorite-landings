package com.example.meteoritelandings.presentation.navigation

sealed class Screen(val route: String) {
    data object MeteoriteList : Screen("meteorite_list")
    data object MeteoriteDetail : Screen("meteorite_detail")

    fun withArgs(vararg args: String) = "$route/${args.joinToString("/")}"
}