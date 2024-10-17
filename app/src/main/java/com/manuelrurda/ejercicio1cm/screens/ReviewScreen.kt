package com.manuelrurda.ejercicio1cm.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.manuelrurda.ejercicio1cm.components.Background

@Composable
fun ReviewScreen(
    originText: String,
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
    freqFlyerNum: String?
) {
    Background {
        Text(text = "hi")
    }
}