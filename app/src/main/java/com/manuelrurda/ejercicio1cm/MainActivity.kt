package com.manuelrurda.ejercicio1cm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.manuelrurda.ejercicio1cm.screens.FlightDetailsScreen
import com.manuelrurda.ejercicio1cm.screens.PassengerDetailsScreen
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
                    startDestination = FlightDetails
                ) {
                    composable<FlightDetails> {
                        FlightDetailsScreen(
                            onNextClick = { originText: String,
                                            destinationText: String,
                                            departureTime: String,
                                            returnTime: String,
                                            departureDate: Long?,
                                            returnDate: Long?
                                ->
                                navController.navigate(
                                    PassengerDetails(
                                        originText,
                                        destinationText,
                                        departureTime,
                                        returnTime,
                                        departureDate,
                                        returnDate
                                    )
                                )
                            }
                        )
                    }
                    composable<PassengerDetails> {
                        val args = it.toRoute<PassengerDetails>()
                        PassengerDetailsScreen(
                            originText = args.originText,
                            destinationText = args.destinationText,
                            departureTime = args.departureTime,
                            returnTime = args.returnTime,
                            departureDate = args.departureDate,
                            returnDate = args.returnDate
                        )
                    }
                }
            }
        }
    }
}

@Serializable
object FlightDetails

@Serializable
data class PassengerDetails(
    val originText: String,
    val destinationText: String,
    val departureTime: String,
    val returnTime: String,
    val departureDate: Long?,
    val returnDate: Long?
)