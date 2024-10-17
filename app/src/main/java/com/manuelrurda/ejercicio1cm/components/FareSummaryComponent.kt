package com.manuelrurda.ejercicio1cm.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.manuelrurda.ejercicio1cm.R
import com.manuelrurda.ejercicio1cm.formatCost
import com.manuelrurda.ejercicio1cm.ui.theme.Green
import com.manuelrurda.ejercicio1cm.ui.theme.Red
import com.manuelrurda.ejercicio1cm.ui.theme.RichBlack
import com.manuelrurda.ejercicio1cm.ui.theme.regularTextStyle
import com.manuelrurda.ejercicio1cm.ui.theme.titleTextStyle

@Composable
fun FareSummaryComponent(
    freqFlyerNum: String,
    flightCost: Int,
    discount: Double,
    totalCost: Double
) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
    ) {
        Text(
            text = stringResource(id = R.string.title_fare_summary),
            style = titleTextStyle
        )
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${stringResource(id = R.string.text_round_trip)}:",
                style = regularTextStyle
            )
            Text(
                text = formatCost(flightCost),
                style = regularTextStyle,
                color = Red
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${stringResource(id = R.string.label_frequent_flyer)}:",
                style = regularTextStyle,
            )
            Text(
                text = if (freqFlyerNum.isNotEmpty()) "-${formatCost(discount)}"
                else "N/A",
                style = regularTextStyle,
                color = if (freqFlyerNum.isNotEmpty()) Green
                else Color.Black,
            )
        }
        Spacer(
            modifier = Modifier
                .padding(vertical = 3.dp)
                .height(1.dp)
                .fillMaxWidth()
                .background(color = RichBlack)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${stringResource(id = R.string.text_total)}:",
                style = regularTextStyle
            )
            Text(
                text = formatCost(totalCost),
                style = regularTextStyle
            )
        }
    }
}