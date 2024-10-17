package com.manuelrurda.ejercicio1cm.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manuelrurda.ejercicio1cm.R
import com.manuelrurda.ejercicio1cm.components.Background
import com.manuelrurda.ejercicio1cm.components.TextButton
import com.manuelrurda.ejercicio1cm.ui.theme.regularTextStyle
import com.manuelrurda.ejercicio1cm.ui.theme.titleTextStyle

@Composable
fun ConfirmationScreen(onNextClick: () -> Unit) {
    Background {
        ElevatedCard(
            modifier = Modifier.padding(all = 8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.title_reservation_confirmed),
                    style = titleTextStyle
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = stringResource(id = R.string.text_confirmation_message),
                    style = regularTextStyle,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextButton(
                    text = stringResource(id = R.string.button_continue),
                    enabled = true, onClick = {
                        onNextClick()
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun Preview4() {
    ConfirmationScreen {

    }
}