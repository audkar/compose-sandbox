package app.nameplaceholder.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier

actual fun getPlatformName(): String {
  return "Android"
}

@Composable
actual fun Modifier.platformShortcuts(callback: () -> Unit): Modifier = this
