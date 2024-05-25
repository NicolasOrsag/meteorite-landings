package com.example.meteoritelandings.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.meteoritelandings.presentation.navigation.Navigation
import com.example.meteoritelandings.presentation.theme.MeteoriteLandingsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeteoriteLandingsTheme {
                Navigation()
            }
        }
    }
}

