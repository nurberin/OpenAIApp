package com.example.openaiapplication.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TextBubble(fromUser: Boolean, text: String) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = if (fromUser) Alignment.CenterEnd else Alignment.CenterStart
    ) {

        Box(
            modifier = Modifier.fillMaxWidth(0.7f),
            contentAlignment = if (fromUser) Alignment.CenterEnd else Alignment.CenterStart
        ) {

            Text(
                text = text, modifier = Modifier
                    .padding(8.dp)
                    .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                    .background(
                        if (fromUser) Color.White else Color.Gray.copy(0.5f),
                        RoundedCornerShape(8.dp)
                    )
                    .padding(8.dp)
            )
        }

    }
}