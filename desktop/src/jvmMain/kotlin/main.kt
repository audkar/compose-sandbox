import androidx.compose.desktop.AppWindowAmbient
import androidx.compose.desktop.Window
import androidx.compose.runtime.*
import app.nameplaceholder.common.App
import java.awt.Dimension

fun main() = Window {
  val appWindow = AppWindowAmbient.current!!

  App()

  onActive {
    appWindow.window.minimumSize = Dimension(500, 400)
  }
}
