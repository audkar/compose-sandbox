package app.nameplaceholder.common

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.plus
import androidx.compose.ui.input.key.shortcuts

actual fun getPlatformName(): String {
  return "Desktop"
}

@Composable
actual fun Modifier.platformShortcuts(callback: () -> Unit): Modifier = this.shortcuts {
  on(Key.CtrlLeft + Key.Enter) {
    callback()
  }
}
