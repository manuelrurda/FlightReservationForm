package com.manuelrurda.ejercicio1cm.screens

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manuelrurda.ejercicio1cm.R
import com.manuelrurda.ejercicio1cm.components.Background
import com.manuelrurda.ejercicio1cm.components.FlightDetailsComponent
import com.manuelrurda.ejercicio1cm.components.TextButton
import com.manuelrurda.ejercicio1cm.ui.theme.HintBlack
import com.manuelrurda.ejercicio1cm.ui.theme.RichBlack
import com.manuelrurda.ejercicio1cm.ui.theme.labelTextStyle
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
    returnDate: Long?,
    onNextClick: (String, String, String, String, String, String, Long?, Long?, String, String, String, String?) -> Unit
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
                FlightDetailsComponent(
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
                    originText = originText,
                    destinationText = destinationText,
                    departureTime = departureTime,
                    returnTime = returnTime,
                    departureSeat = departureSeat,
                    returnSeat = returnSeat,
                    departureDate = departureDate,
                    returnDate = returnDate,
                    nameTextState = nameTextState,
                    lastNameTextState = lastNameTextState,
                    emailTextState = emailTextState,
                    frequentFlyerTextState = frequentFlyerTextState,
                    onNextClick = onNextClick
                )
            }
        }
    }
}

@Composable
fun PassengerDetailsForm(
    nameTextState: MutableState<String>,
    lastNameTextState: MutableState<String>,
    emailTextState: MutableState<String>,
    frequentFlyerTextState: MutableState<String>,
    onNextClick: (String, String, String, String, String, String, Long?, Long?, String, String, String, String?) -> Unit,
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
            isValid = { email: String -> isValidEmail(email) },
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
                keyboardType = KeyboardType.NumberPassword,
            ),
            isValid = { freqFlyerNum: String -> isValidFreqFlyer(freqFlyerNum) },
            maxLength = 8
        )
        Spacer(modifier = Modifier.height(15.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            TextButton(
                text = stringResource(id = R.string.button_next),
                enabled = nameTextState.value.isNotEmpty() &&
                        lastNameTextState.value.isNotEmpty() &&
                        isValidEmail(emailTextState.value) &&
                        isValidFreqFlyer(frequentFlyerTextState.value),
                onClick = {
                    onNextClick(
                        originText,
                        destinationText,
                        departureTime,
                        returnTime,
                        departureSeat,
                        returnSeat,
                        departureDate,
                        returnDate,
                        nameTextState.value,
                        lastNameTextState.value,
                        emailTextState.value,
                        frequentFlyerTextState.value
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LabeledTextField(
    labelText: String,
    placeholder: String,
    stateHolder: MutableState<String>,
    isValid: ((String) -> Boolean)? = null,
    keyboardOptions: KeyboardOptions? = null,
    maxLength: Int? = null
) {
    var isError by remember { mutableStateOf(false) };

    Text(text = labelText, style = labelTextStyle)
    Spacer(modifier = Modifier.height(5.dp))
    OutlinedTextField(
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = HintBlack,
            focusedBorderColor = if (isError) Color.Red else RichBlack
        ),
        modifier = Modifier.fillMaxWidth(),
        value = stateHolder.value,
        onValueChange = { text: String ->
            stateHolder.value = if (maxLength == null || text.length <= maxLength) text
            else stateHolder.value
            if (isValid != null) {
                isError = !isValid(text)
            }
        },
        singleLine = true,
        textStyle = textFieldTextStyle,
        placeholder = { Text(text = placeholder, style = textFieldTextStyle) },
        isError = isError,
        keyboardOptions = keyboardOptions ?: KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next
        ),
    )
}

fun isValidEmail(email: String): Boolean {
    return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun isValidFreqFlyer(freqFlyerNum: String): Boolean {
    return freqFlyerNum.isEmpty() || freqFlyerNum.length == 8
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
        returnDate = 1728695008853,
        onNextClick = { _, _, _, _, _, _, _, _, _, _, _, _ ->

        }
    )
}