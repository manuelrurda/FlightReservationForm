package com.manuelrurda.ejercicio1cm.screens

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manuelrurda.ejercicio1cm.R
import com.manuelrurda.ejercicio1cm.components.Background
import com.manuelrurda.ejercicio1cm.components.TextButton
import com.manuelrurda.ejercicio1cm.convertMillisToDate
import com.manuelrurda.ejercicio1cm.ui.theme.HintBlack
import com.manuelrurda.ejercicio1cm.ui.theme.labelTextStyle
import com.manuelrurda.ejercicio1cm.ui.theme.lightGrayTextStyle
import com.manuelrurda.ejercicio1cm.ui.theme.regularTextStyle
import com.manuelrurda.ejercicio1cm.ui.theme.textFieldTextStyle
import com.manuelrurda.ejercicio1cm.ui.theme.titleTextStyle

@Composable
fun PassengerDetailsScreen(
    originText: String,
    destinationText: String,
    departureTime: String,
    returnTime: String,
    departureSeat: String,
    returnSeat: String,
    departureDate: Long?,
    returnDate: Long?
) {
    val nameTextState = rememberSaveable { mutableStateOf("") }
    val lastNameTextState = rememberSaveable { mutableStateOf("") }
    val emailTextState = rememberSaveable { mutableStateOf("") }
    val frequentFlyerTextState = rememberSaveable { mutableStateOf("") }

    Background {
        ElevatedCard(
            modifier = Modifier.padding(all = 8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ) {
            Column(modifier = Modifier.padding(all = 5.dp)) {
                FlightDetailsHeader(
                    originText,
                    destinationText,
                    departureTime,
                    returnTime,
                    departureSeat,
                    returnSeat,
                    departureDate,
                    returnDate
                )
                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(color = HintBlack)
                )
                PassengerDetailsForm(
                    nameTextState,
                    lastNameTextState,
                    emailTextState,
                    frequentFlyerTextState
                )
            }
        }
    }
}


@Composable
fun FlightDetailsHeader(
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
                }
            }
        }
    }
}

@Composable
fun PassengerDetailsForm(
    nameTextState: MutableState<String>,
    lastNameTextState: MutableState<String>,
    emailTextState: MutableState<String>,
    frequentFlyerTextState: MutableState<String>
) {
    Column(
        modifier = Modifier
            .padding(10.dp),
    ) {
        Text(text = stringResource(id = R.string.title_passenger_details), style = titleTextStyle)
        Spacer(modifier = Modifier.height(5.dp))
        LabeledTextField(
            labelText = stringResource(id = R.string.label_name),
            stateHolder = nameTextState,
            placeholder = stringResource(id = R.string.hint_john)
        )
        Spacer(modifier = Modifier.height(15.dp))
        LabeledTextField(
            labelText = stringResource(id = R.string.label_last_name),
            stateHolder = lastNameTextState,
            placeholder = stringResource(id = R.string.hint_doe)
        )
        Spacer(modifier = Modifier.height(15.dp))
        LabeledTextField(
            labelText = stringResource(id = R.string.label_email),
            stateHolder = emailTextState,
            placeholder = stringResource(id = R.string.hint_example_email),
            validator = { email: String -> validateEmail(email) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email
            )
        )
        Spacer(modifier = Modifier.height(15.dp))
        LabeledTextField(
            labelText = stringResource(id = R.string.label_frequent_flyer),
            stateHolder = frequentFlyerTextState,
            placeholder = stringResource(id = R.string.hint_frq_flyer),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.NumberPassword
            )
        )
        Spacer(modifier = Modifier.height(15.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            TextButton(
                text = stringResource(id = R.string.button_next),
                enabled = true
            ) {

            }
        }
    }
}

fun validateEmail(email: String): Boolean {
    return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

@Composable
fun LabeledTextField(
    labelText: String,
    placeholder: String,
    stateHolder: MutableState<String>,
    validator: ((String) -> Boolean)? = null,
    keyboardOptions: KeyboardOptions? = null
) {
    Text(text = labelText, style = labelTextStyle)
    Spacer(modifier = Modifier.height(5.dp))
    TextField(
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = HintBlack
        ),
        modifier = Modifier.fillMaxWidth(),
        value = stateHolder.value,
        onValueChange = {
            stateHolder.value = it
        },
        singleLine = true,
        textStyle = textFieldTextStyle,
        placeholder = { Text(text = placeholder, style = textFieldTextStyle) },
        isError = (validator != null && validator(stateHolder.value)),
        keyboardOptions = keyboardOptions ?: KeyboardOptions.Default
    )
}

@Preview(showBackground = true)
@Composable
fun Test() {
    PassengerDetailsScreen(
        originText = "Cancun",
        destinationText = "Los Cabos",
        departureTime = "10:00",
        returnTime = "18:00",
        departureSeat = "A2",
        returnSeat = "E6",
        departureDate = 1728685008853,
        returnDate = 1728695008853
    )
}