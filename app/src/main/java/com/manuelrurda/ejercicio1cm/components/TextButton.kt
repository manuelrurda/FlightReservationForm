package com.manuelrurda.ejercicio1cm.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.manuelrurda.ejercicio1cm.ui.theme.RichBlack
import com.manuelrurda.ejercicio1cm.ui.theme.buttonTextStyle

@Composable
fun TextButton(text: String, enabled: Boolean, onClick: () -> Unit) {
    Button(
        colors = ButtonDefaults.buttonColors(containerColor = RichBlack),
        enabled = enabled,
        onClick = onClick
    ) {
        Text(
            text = text,
            style = buttonTextStyle
        )
    }
}