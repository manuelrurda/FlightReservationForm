package com.manuelrurda.ejercicio1cm

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun convertMillisToDate(millis: Long?): String {
    val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    return formatter.format(Date((millis ?: 0)))
}

fun generateRandomSeat(): String {
    val seat = (65 + (0..3).random()).toChar()
    val row = (0..25).random()
    return "$seat$row"
}