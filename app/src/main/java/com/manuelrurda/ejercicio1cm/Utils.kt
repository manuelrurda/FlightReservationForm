package com.manuelrurda.ejercicio1cm

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.cos

fun convertMillisToDate(millis: Long?): String {
    val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    return formatter.format(Date((millis ?: 0)))
}

fun generateRandomSeat(): String {
    val seat = (65 + (0..3).random()).toChar()
    val row = (0..25).random()
    return "$seat$row"
}

fun generateFlightCost(): Int {
    return (3000..10000).random()
}

fun formatCost(cost: Int): String {
    return formatCost(cost.toDouble())
}

fun formatCost(cost: Double): String {
    val formatter = NumberFormat.getNumberInstance(Locale.US).apply {
        maximumFractionDigits = 2
        minimumFractionDigits = 2
        isGroupingUsed = true
    }
    return "$${formatter.format(cost)}"
}