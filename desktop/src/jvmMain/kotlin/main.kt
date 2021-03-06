import androidx.compose.desktop.LocalAppWindow
import androidx.compose.desktop.Window
import app.nameplaceholder.common.App
import java.awt.Dimension

fun main() = Window {
  val appWindow = LocalAppWindow.current

  appWindow.window.minimumSize = Dimension(500, 400)

  App()
}
