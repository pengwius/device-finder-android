package pengwius.devicefinder.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val LightColorScheme = lightColorScheme(
    primary = Color(0xFFC6AFFF),
    onPrimary = Color(0xFF1C1B1F),
    primaryContainer = Color(0xFFE6DDFF),
    onPrimaryContainer = Color(0xFF1C1B1F),

    secondary = Color(0xFFAD8CFF),
    onSecondary = Color(0xFF1C1B1F),
    secondaryContainer = Color(0xFFD6C6FF),
    onSecondaryContainer = Color(0xFF1C1B1F),

    tertiary = Color(0xFF9C7EE6),
    onTertiary = Color(0xFF1C1B1F),

    background = Color(0xFFF7F4FF),
    surface = Color(0xFFFFFFFF),
    surfaceVariant = Color(0xFFE6DDFF),
    onSurface = Color(0xFF1C1B1F)
)

val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF705BA6),
    onPrimary = Color(0xFFF7F4FF),
    primaryContainer = Color(0xFF5C4A73),
    onPrimaryContainer = Color(0xFFE6DDFF),

    secondary = Color(0xFF8A70CC),
    onSecondary = Color(0xFFF7F4FF),
    secondaryContainer = Color(0xFF705BA6),
    onSecondaryContainer = Color(0xFFE6DDFF),

    tertiary = Color(0xFF9C7EE6),
    onTertiary = Color(0xFFF7F4FF),

    background = Color(0xFF120E1E),
    surface = Color(0xFF15121F),
    surfaceVariant = Color(0xFF17142B),
    onSurface = Color(0xFFF7F4FF)
)

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val dark = isSystemInDarkTheme()
    val colors = if (dark) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}
