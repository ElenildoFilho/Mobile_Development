package com.example.a4chat.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFF0078D4),
    onPrimary = Color.White,
    secondary = Color(0xFFE6EE9C),
    onSecondary = Color.Black,
    surface = Color(0xFFF5F5F5),
    onSurface = Color.Black,
    background = Color(0xFFF5F5F5),
    onBackground = Color.Black
)

@Composable
fun MsgAppTheme(
    darkTheme: Boolean = false, // Você pode implementar controle de tema dinâmico
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography,
        content = content
    )
}