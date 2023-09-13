package com.unlimit.presentation.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val lightBackgroundColor = Color.White
private val primaryColor = Color(0xFF08a0e9)

val lightThemeColors = lightColors(
    primary = primaryColor,
    background = lightBackgroundColor,
    surface = lightBackgroundColor
)

@Composable
fun JokeText(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .padding(16.dp)
            .background(Color.White), // Background color
        fontSize = 18.sp, // Font size
        fontWeight = FontWeight.Bold, // Font weight
        fontFamily = FontFamily.SansSerif, // Font family
        color = Color.Black, // Text color
        textAlign = TextAlign.Center, // Text alignment
        letterSpacing = 0.1.sp, // Letter spacing
        lineHeight = 24.sp // Line height
    )
}