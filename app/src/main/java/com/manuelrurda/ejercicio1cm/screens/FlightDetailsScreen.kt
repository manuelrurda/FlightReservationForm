package com.manuelrurda.ejercicio1cm.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.core.content.ContextCompat.getString
import com.manuelrurda.ejercicio1cm.R
import com.manuelrurda.ejercicio1cm.convertMillisToDate
import com.manuelrurda.ejercicio1cm.generateRandomSeat
import com.manuelrurda.ejercicio1cm.ui.theme.PoppinsFontFamily
import com.manuelrurda.ejercicio1cm.ui.theme.RichBlack
import com.manuelrurda.ejercicio1cm.ui.theme.Saffrom
import com.manuelrurda.ejercicio1cm.ui.theme.buttonTextStyle
import com.manuelrurda.ejercicio1cm.ui.theme.labelTextStyle
import com.manuelrurda.ejercicio1cm.ui.theme.logoTextStyle
import com.manuelrurda.ejercicio1cm.ui.theme.textFieldTextStyle
import java.util.Calendar

@Composable
fun FlightDetailsScreen(
    onNextClick: (
        originText: String,
        destinationText: String,
        departureTime: String,
        returnTime: String,
        departureSeat: String,
        returnSeat: String,
        departureDate: Long?,
        returnDate: Long?
    ) -> Unit
) {
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
        FlightDetailsCard(onNextClick)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(text = "Logo", style = logoTextStyle, color = RichBlack)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightDetailsCard(
    onNextClick: (originText: String, destinationText: String, departureTime: String, returnTime: String, departureSeat: String, returnSeat: String, departureDate: Long?, returnDate: Long?) -> Unit
) {

    val context = LocalContext.current

    val destinations = listOf(
        stringResource(id = R.string.menu_item_gdl),
        stringResource(id = R.string.menu_item_mty),
        stringResource(id = R.string.menu_item_tij),
        stringResource(id = R.string.menu_item_cabos),
        stringResource(id = R.string.menu_item_mxcity),
        stringResource(id = R.string.menu_item_cancun)
    )
    val flightTimes = listOf(
        stringResource(id = R.string.menu_item_700),
        stringResource(id = R.string.menu_item_1000),
        stringResource(id = R.string.menu_item_1400),
        stringResource(id = R.string.menu_item_1800)
    )

    val originTextState = rememberSaveable { mutableStateOf("") }
    val destinationTextState = rememberSaveable { mutableStateOf("") }
    val departureTimeState = rememberSaveable { mutableStateOf("") }
    val returnTimeState = rememberSaveable { mutableStateOf("") }
    val departureDateState = rememberDatePickerState()
    val returnDateState = rememberDatePickerState()

    ElevatedCard(
        modifier = Modifier.padding(all = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Row(modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)) {
            Column(
                modifier = Modifier
                    .weight(1F)
                    .padding(end = 5.dp)
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 7.dp),
                    text = stringResource(id = R.string.label_origin),
                    style = labelTextStyle
                )
                DropDownMenu(
                    menuItems = destinations,
                    placeholderText = stringResource(id = R.string.hint_select_option),
                    textState = originTextState,
                    validator = {
                        validateDestinationText(
                            context,
                            originTextState,
                            destinationTextState
                        )
                    }
                )
            }
            Column(
                modifier = Modifier
                    .weight(1F)
                    .padding(start = 5.dp)
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 7.dp),
                    text = stringResource(id = R.string.label_destination),
                    style = labelTextStyle
                )
                DropDownMenu(
                    menuItems = destinations,
                    textState = destinationTextState,
                    placeholderText = stringResource(id = R.string.hint_select_option),
                    validator = {
                        validateDestinationText(
                            context,
                            destinationTextState,
                            originTextState
                        )
                    })
            }
        }
        Row(modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)) {
            Column(
                modifier = Modifier
                    .weight(1F)
                    .padding(end = 5.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.label_departure_date),
                    style = labelTextStyle
                )
                Spacer(modifier = Modifier.height(5.dp))
                DestinationDatePicker(
                    datePickerState = departureDateState,
                    validator = { date ->
                        validateDepartureDate(date, returnDateState)
                    })
            }
            Column(
                modifier = Modifier
                    .weight(1F)
                    .padding(start = 5.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.label_departure_time),
                    style = labelTextStyle
                )
                Spacer(modifier = Modifier.height(5.dp))
                DropDownMenu(
                    menuItems = flightTimes,
                    placeholderText = stringResource(R.string.hint_select_option),
                    textState = departureTimeState,
                    validator = {})
            }
        }
        Row(modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)) {
            Column(
                modifier = Modifier
                    .weight(1F)
                    .padding(end = 5.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.label_return_date),
                    style = labelTextStyle
                )
                Spacer(modifier = Modifier.height(5.dp))
                DestinationDatePicker(
                    datePickerState = returnDateState,
                    validator = { date ->
                        validateReturnDate(date, departureDateState)
                    })
            }
            Column(
                modifier = Modifier
                    .weight(1F)
                    .padding(start = 5.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.label_return_time),
                    style = labelTextStyle
                )
                Spacer(modifier = Modifier.height(5.dp))
                DropDownMenu(
                    menuItems = flightTimes,
                    placeholderText = stringResource(id = R.string.hint_select_option),
                    textState = returnTimeState,
                    validator = {
                    })
            }
        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = RichBlack),
                enabled = (
                        originTextState.value.isNotEmpty() &&
                                destinationTextState.value.isNotEmpty() &&
                                departureTimeState.value.isNotEmpty() &&
                                returnTimeState.value.isNotEmpty() &&
                                departureDateState.selectedDateMillis != null &&
                                returnDateState.selectedDateMillis != null
                        ),
                onClick = {
                    onNextClick(
                        originTextState.value,
                        destinationTextState.value,
                        departureTimeState.value,
                        returnTimeState.value,
                        generateRandomSeat(),
                        generateRandomSeat(),
                        departureDateState.selectedDateMillis,
                        returnDateState.selectedDateMillis
                    )
                }) {
                Text(
                    text = stringResource(id = R.string.button_next),
                    style = buttonTextStyle
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu(
    menuItems: List<String>,
    placeholderText: String,
    textState: MutableState<String>,
    validator: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {
        expanded = !expanded
    }) {
        TextField(
            modifier = Modifier
                .menuAnchor(),
            value = textState.value,
            placeholder = {
                Text(
                    text = placeholderText,
                    style = textFieldTextStyle
                )
            },
            onValueChange = {},
            readOnly = true,
            singleLine = false,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            textStyle = textFieldTextStyle
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }) {
            menuItems.forEach { destination ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = destination,
                            fontFamily = PoppinsFontFamily,
                            fontSize = 12.sp
                        )
                    },
                    onClick = {
                        textState.value = destination
                        validator()
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DestinationDatePicker(datePickerState: DatePickerState, validator: (Long) -> Boolean) {
    var expanded by remember { mutableStateOf(false) }
    val selectedDate =
        datePickerState.selectedDateMillis?.let {
            convertMillisToDate(it)
        } ?: ""

    Box {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            value = selectedDate,
            onValueChange = {},
            placeholder = {
                Text(
                    text = stringResource(id = R.string.hint_select_date),
                    style = textFieldTextStyle
                )
            },
            textStyle = textFieldTextStyle,
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Select date"
                )
            }
        )

        Box(modifier = Modifier
            .matchParentSize()
            .clickable { expanded = !expanded })
    }

    if (expanded) {
        Popup(
            onDismissRequest = { expanded = false },
            alignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .shadow(elevation = 4.dp)
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp)
            ) {
                Column {
                    DatePicker(
                        state = datePickerState,
                        showModeToggle = false,
                        dateValidator = { date -> validator(date) }
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = {
                                expanded = false
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = RichBlack
                            )
                        ) {
                            Text(
                                text = stringResource(id = R.string.button_select),
                                style = buttonTextStyle
                            )
                        }
                    }
                }
            }
        }
    }

}

fun validateDestinationText(
    context: Context,
    updatedTextState: MutableState<String>,
    comparedTextState: MutableState<String>
) {
    if (updatedTextState.value == comparedTextState.value) {
        Toast.makeText(
            context,
            getString(context, R.string.error_same_destination),
            Toast.LENGTH_LONG
        ).show()
        updatedTextState.value = ""
    }
}

@OptIn(ExperimentalMaterial3Api::class)
fun validateDepartureDate(
    date: Long,
    returnDateState: DatePickerState
): Boolean {
    val currentDate = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    if (returnDateState.selectedDateMillis != null) {
        return date < returnDateState.selectedDateMillis!! &&
                date >= currentDate.time.time
    }

    return date >= currentDate.time.time
}

@OptIn(ExperimentalMaterial3Api::class)
fun validateReturnDate(
    date: Long,
    departureDateState: DatePickerState
): Boolean {
    val currentDate = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    if (departureDateState.selectedDateMillis != null) {
        return date > departureDateState.selectedDateMillis!!
    }

    return date >= currentDate.time.time
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    FlightDetailsScreen(onNextClick = { _: String,
                                      _: String,
                                      _: String,
                                      _: String,
                                      _: String,
                                      _: String,
                                      _: Long?,
                                      _: Long?
        ->
    })
}
