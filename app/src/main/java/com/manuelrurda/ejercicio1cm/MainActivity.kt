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
import com.manuelrurda.ejercicio1cm.Model.FlightDetailsData
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
                                            departureSeat: String,
                                            returnSeat: String,
                                            departureDate: Long?,
                                            returnDate: Long?
                                ->
                                navController.navigate(
                                    PassengerDetails(
                                        FlightDetailsData(
                                            originText,
                                            destinationText,
                                            departureTime,
                                            returnTime,
                                            departureSeat,
                                            returnSeat,
                                            departureDate,
                                            returnDate
                                        )
                                    )
                                )
                            }
                        )
                    }
                    composable<PassengerDetails> {
                        val args = it.toRoute<PassengerDetails>()
                        PassengerDetailsScreen(
                            FlightDetailsData(
                                originText = args.flightDetailsData.originText,
                                destinationText = args.flightDetailsData.destinationText,
                                departureTime = args.flightDetailsData.departureTime,
                                returnTime = args.flightDetailsData.returnTime,
                                departureSeat = args.flightDetailsData.departureSeat,
                                returnSeat = args.flightDetailsData.returnSeat,
                                departureDate = args.flightDetailsData.departureDate,
                                returnDate = args.flightDetailsData.returnDate
                            )
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
    val flightDetailsData: FlightDetailsData
)