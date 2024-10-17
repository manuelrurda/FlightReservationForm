package com.manuelrurda.ejercicio1cm.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.manuelrurda.ejercicio1cm.R
import com.manuelrurda.ejercicio1cm.ui.theme.lightGrayTextStyle
import com.manuelrurda.ejercicio1cm.ui.theme.regularTextStyle
import com.manuelrurda.ejercicio1cm.ui.theme.titleTextStyle

@Composable
fun PassengerDetailsComponent(
    name: String,
    lastName: String,
    email: String,
    freqFlyerNum: String?
) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
    ) {
        Text(
            text = stringResource(id = R.string.title_passenger_details),
            style = titleTextStyle
        )
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Column {
                Text(
                    text = stringResource(id = R.string.label_name),
                    style = lightGrayTextStyle
                )
                Text(text = name, style = regularTextStyle)
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = stringResource(id = R.string.label_last_name),
                    style = lightGrayTextStyle
                )
                Text(text = lastName, style = regularTextStyle)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = stringResource(id = R.string.label_email), style = lightGrayTextStyle)
        Text(text = email, style = regularTextStyle)
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.label_frequent_flyer),
            style = lightGrayTextStyle
        )
        Text(
            text = freqFlyerNum ?: stringResource(id = R.string.text_na),
            style = regularTextStyle
        )
    }
}