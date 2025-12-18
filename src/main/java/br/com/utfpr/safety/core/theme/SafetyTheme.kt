package br.com.utfpr.safety.core.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable


@Composable
fun SafetyTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkSafetyColorScheme,
        typography = Typography,
        content = content
    )
}
