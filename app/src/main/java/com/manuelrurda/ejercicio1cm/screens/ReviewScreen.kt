package com.manuelrurda.ejercicio1cm.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manuelrurda.ejercicio1cm.R
import com.manuelrurda.ejercicio1cm.components.Background
import com.manuelrurda.ejercicio1cm.components.FareSummaryComponent
import com.manuelrurda.ejercicio1cm.components.FlightDetailsComponent
import com.manuelrurda.ejercicio1cm.components.PassengerDetailsComponent
import com.manuelrurda.ejercicio1cm.components.TextButton
import com.manuelrurda.ejercicio1cm.generateFlightCost
import com.manuelrurda.ejercicio1cm.ui.theme.HintBlack

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
    freqFlyerNum: String
) {
    Background {
        ElevatedCard(
            modifier = Modifier.padding(all = 8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ) {

            val flightCost = remember { generateFlightCost() }
            val discount = remember { flightCost * .15 }
            val totalCost = remember {
                if (freqFlyerNum.isNotEmpty()) flightCost - discount
                else flightCost.toDouble()
            }



            Column(modifier = Modifier.padding(5.dp)) {
                FlightDetailsComponent(
                    originText = originText,
                    destinationText = destinationText,
                    departureTime = departureTime,
                    returnTime = returnTime,
                    departureSeat = departureSeat,
                    returnSeat = returnSeat,
                    departureDate = departureDate,
                    returnDate = returnDate
                )
                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(color = HintBlack)
                )
                PassengerDetailsComponent(
                    name = name,
                    lastName = lastName,
                    email = email,
                    freqFlyerNum = freqFlyerNum
                )
                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(color = HintBlack)
                )
                FareSummaryComponent(
                    freqFlyerNum = freqFlyerNum,
                    flightCost = flightCost,
                    discount = discount,
                    totalCost = totalCost
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    contentAlignment = Alignment.Center
                ) {
                    TextButton(text = stringResource(id = R.string.button_reserve),
                        enabled = true,
                        onClick = {})
                }
            }
        }
    }
}

@Preview
@Composable
fun Preview3() {
    ReviewScreen(
        originText = "Los Cabos",
        destinationText = "Monterrey",
        departureTime = "14:00",
        returnTime = "7:00",
        departureSeat = "A13",
        returnSeat = "E23",
        departureDate = 1729128569554,
        returnDate = 1729128569554,
        name = "Manuel",
        lastName = "Rodriguez",
        email = "manuelrurda@gmail.com",
        freqFlyerNum = "12345678"
    )
}