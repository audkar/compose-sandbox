package app.nameplaceholder.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun App() {
  var text by remember { mutableStateOf("Hi") }
  var textF by remember { mutableStateOf(TextFieldValue()) }

  MaterialTheme(
    colors = darkColors(),
  ) {
    Surface(color = MaterialTheme.colors.surface) {
      Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
      ) {
        OutlinedTextField(
          value = textF,
          onValueChange = {
            textF = it
          },
        )
        Divider(
          thickness = 16.dp,
          color = Color.Transparent,
        )
        Button(onClick = {
          text = "Hello, ${getPlatformName()}"
        }) {
          Text(text)
        }
      }
    }
  }
}
