package app.nameplaceholder.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember

@Composable
actual fun <VM : IViewModel<T>, T> vModel(
  key: String?,
  factory: () -> VM
): VM {
  val vm = remember { factory() }

  DisposableEffect(Unit) {
    onDispose {
      vm.dispose()
    }
  }

  return vm
}

