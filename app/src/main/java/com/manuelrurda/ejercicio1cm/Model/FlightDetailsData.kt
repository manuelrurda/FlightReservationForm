package com.manuelrurda.ejercicio1cm.Model

import kotlinx.serialization.Serializable

@Serializable
data class FlightDetailsData(
    val originText: String,
    val destinationText: String,
    val departureTime: String,
    val returnTime: String,
    val departureSeat: String,
    val returnSeat: String,
    val departureDate: Long?,
    val returnDate: Long?
)