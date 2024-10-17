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
import com.manuelrurda.ejercicio1cm.screens.ConfirmationScreen
import com.manuelrurda.ejercicio1cm.screens.FlightDetailsScreen
import com.manuelrurda.ejercicio1cm.screens.PassengerDetailsScreen
import com.manuelrurda.ejercicio1cm.screens.ReviewScreen
import com.manuelrurda.ejercicio1cm.ui.theme.Ejercicio1CMTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

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
                            departureSeat = args.departureSeat,
                            returnSeat = args.returnSeat,
                            departureDate = args.departureDate,
                            returnDate = args.returnDate,
                            onNextClick = { originText: String,
                                            destinationText: String,
                                            departureTime: String,
                                            returnTime: String,
                                            departureSeat: String,
                                            returnSeat: String,
                                            departureDate: Long?,
                                            returnDate: Long?,
                                            name: String,
                                            lastName: String,
                                            email: String,
                                            freqFlyerNum: String ->

                                navController.navigate(
                                    Review(
                                        originText,
                                        destinationText,
                                        departureTime,
                                        returnTime,
                                        departureSeat,
                                        returnSeat,
                                        departureDate,
                                        returnDate,
                                        name,
                                        lastName,
                                        email,
                                        freqFlyerNum
                                    )
                                )
                            }
                        )
                    }
                    composable<Review> {
                        val args = it.toRoute<Review>()
                        ReviewScreen(
                            originText = args.originText,
                            destinationText = args.destinationText,
                            departureTime = args.departureTime,
                            returnTime = args.returnTime,
                            departureSeat = args.departureSeat,
                            returnSeat = args.returnSeat,
                            departureDate = args.departureDate,
                            returnDate = args.returnDate,
                            name = args.name,
                            lastName = args.lastName,
                            email = args.email,
                            freqFlyerNum = args.freqFlyerNum,
                            onNextClick = {
                                navController.navigate(Confirmation)
                            }
                        )
                    }
                    composable<Confirmation> {
                        ConfirmationScreen(
                            onNextClick = {
                                navController.navigate(FlightDetails)
                            }
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
object Confirmation

@Serializable
data class PassengerDetails(
    val originText: String,
    val destinationText: String,
    val departureTime: String,
    val returnTime: String,
    val departureSeat: String,
    val returnSeat: String,
    val departureDate: Long?,
    val returnDate: Long?
)

@Serializable
data class Review(
    val originText: String,
    val destinationText: String,
    val departureTime: String,
    val returnTime: String,
    val departureSeat: String,
    val returnSeat: String,
    val departureDate: Long?,
    val returnDate: Long?,
    val name: String,
    val lastName: String,
    val email: String,
    val freqFlyerNum: String
)