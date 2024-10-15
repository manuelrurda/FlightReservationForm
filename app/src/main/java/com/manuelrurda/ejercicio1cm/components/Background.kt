package com.manuelrurda.ejercicio1cm.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.manuelrurda.ejercicio1cm.R
import com.manuelrurda.ejercicio1cm.screens.FlightDetailsHeader
import com.manuelrurda.ejercicio1cm.screens.PassengerDetailsForm
import com.manuelrurda.ejercicio1cm.ui.theme.Saffrom

@Composable
fun Background(content: @Composable BoxScope.() -> Unit) {
    Image(
        painter = painterResource(id = R.drawable.image_flight_bg),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Saffrom
                    ),
                    startY = 200f,
                    endY = 1200f
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}