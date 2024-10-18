package com.manuelrurda.ejercicio1cm.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.manuelrurda.ejercicio1cm.R
import com.manuelrurda.ejercicio1cm.convertMillisToDate
import com.manuelrurda.ejercicio1cm.ui.theme.labelTextStyle
import com.manuelrurda.ejercicio1cm.ui.theme.lightGrayTextStyle
import com.manuelrurda.ejercicio1cm.ui.theme.regularTextStyle
import com.manuelrurda.ejercicio1cm.ui.theme.titleTextStyle

@Composable
fun FlightDetailsComponent(
    originText: String,
    destinationText: String,
    departureTime: String,
    returnTime: String,
    departureSeat: String,
    returnSeat: String,
    departureDate: Long?,
    returnDate: Long?
) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
    ) {
        Text(text = stringResource(id = R.string.title_flight_details), style = titleTextStyle)
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column {
                Text(text = stringResource(id = R.string.label_origin), style = lightGrayTextStyle)
                Text(text = originText, style = labelTextStyle)
                Text(
                    text = "${convertMillisToDate(departureDate)} | $departureTime",
                    style = regularTextStyle
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.icon_plane_seat),
                        contentDescription = "", modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(text = departureSeat, style = regularTextStyle)
                    Spacer(modifier = Modifier.width(3.dp))
                    SeatsInfoButton()
                }
            }
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.icon_two_arrows),
                contentDescription = "", modifier = Modifier.size(40.dp)
            )
            Column {
                Text(text = stringResource(id = R.string.label_return), style = lightGrayTextStyle)
                Text(text = destinationText, style = labelTextStyle)
                Text(
                    text = "${convertMillisToDate(returnDate)} | $returnTime",
                    style = regularTextStyle
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.icon_plane_seat),
                        contentDescription = "", modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(text = returnSeat, style = regularTextStyle)
                    Spacer(modifier = Modifier.width(3.dp))
                    SeatsInfoButton()
                }
            }
        }
    }
}