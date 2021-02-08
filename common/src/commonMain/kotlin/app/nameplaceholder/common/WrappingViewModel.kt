package app.nameplaceholder.common

import androidx.compose.runtime.*
import kotlinx.coroutines.flow.StateFlow

@Composable
expect fun <VM : IViewModel<T>, T> vModel(
  key: String? = null,
  factory: () -> VM
): VM

interface IViewModel<T> {
  val state: StateFlow<T>

  fun dispose()
}
