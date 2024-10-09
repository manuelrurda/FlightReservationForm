package com.manuelrurda.ejercicio1cm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.savedstate.SavedStateRegistryController
import com.manuelrurda.ejercicio1cm.screens.FlightDetailsScreen
import com.manuelrurda.ejercicio1cm.ui.theme.Ejercicio1CMTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ejercicio1CMTheme {
                navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = FlightDetailsScreen
                ) {
                    composable<FlightDetailsScreen> { FlightDetailsScreen() }
                }
            }
        }
    }
}

@Serializable
object FlightDetailsScreen