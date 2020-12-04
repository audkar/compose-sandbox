package app.nameplaceholder.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.darkColors
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun App() {
  var messages by remember { mutableStateOf(listOf<String>()) }
  var currentMessage by remember { mutableStateOf(TextFieldValue()) }

  fun sendMessage() {
    if (currentMessage.text.isNotBlank()) {
      messages = messages + currentMessage.text
      currentMessage = TextFieldValue()
    }
  }

  MaterialTheme(
    colors = darkColors(),
  ) {
    Surface(color = MaterialTheme.colors.surface) {
      Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
      ) {
        LazyColumn(
          modifier = Modifier.weight(1f).background(color = MaterialTheme.colors.primarySurface)
            .fillMaxWidth(),
        ) {
          items(messages) { item ->
            Text("User1: $item")
          }
        }
        Divider(modifier = Modifier.padding(vertical = 8.dp))
        Row(
          verticalAlignment = Alignment.CenterVertically,
        ) {
          OutlinedTextField(
            value = currentMessage,
            maxLines = 3,
            modifier = Modifier.weight(1f),
            onValueChange = {
              currentMessage = it
            },
          )
          Spacer(Modifier.width(8.dp))
          Button(onClick = ::sendMessage) {
            Text("Send")
          }
        }
      }
    }
  }
}

