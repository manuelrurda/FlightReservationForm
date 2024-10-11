package com.manuelrurda.ejercicio1cm.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun PassengerDetailsScreen(
    originText: String,
    destinationText: String,
    departureTime: String,
    returnTime: String,
    departureDate: Long?,
    returnDate: Long?
) {
    Column {
        Text(text = originText)
        Text(text = destinationText)
        Text(text = departureTime)
        Text(text = returnTime)
    }
}