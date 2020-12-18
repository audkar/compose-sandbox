package app.nameplaceholder.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.darkColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.primarySurface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

data class Message(
  val userName: String,
  val message: String,
  val createTime: Instant,
)

@Composable
fun App() {
  var messages by remember { mutableStateOf(listOf<Message>()) }
  var currentMessage by remember { mutableStateOf(TextFieldValue()) }
  val listState = rememberLazyListState()

  fun sendMessage() {
    if (currentMessage.text.isNotBlank()) {
      messages = messages + Message(
        userName = "User1",
        message = currentMessage.text,
        createTime = Clock.System.now(),
      )
      currentMessage = TextFieldValue()
    }
  }

  fun deleteMessage(message: Message) {
    messages = messages.filter { it.createTime != message.createTime }
  }

  MaterialTheme(
    colors = darkColors(),
  ) {
    Surface(color = MaterialTheme.colors.background) {
      Column(
        modifier = Modifier.fillMaxSize(),
      ) {
        LazyColumn(
          modifier = Modifier
            .weight(1f)
            .background(color = MaterialTheme.colors.primarySurface)
            .fillMaxWidth(),
          contentPadding = PaddingValues(16.dp),
          state = listState,
        ) {
          val size = messages.size
          itemsIndexed(messages) { index, item ->
            MessageListItem(
              message = item,
              isLast = index == size - 1,
              onDeleteClick = ::deleteMessage
            )
          }
        }
        Divider()
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.padding(16.dp),
        ) {
          OutlinedTextField(
            value = currentMessage,
            maxLines = 3,
            modifier = Modifier
              .weight(1f)
              .platformShortcuts { sendMessage() },
            label = { Text("Message") },
            onValueChange = {
              currentMessage = it
            },
          )
          Spacer(Modifier.width(8.dp))
          Button(
            onClick = ::sendMessage,
          ) {
            Text("Send")
          }
        }
      }
    }
  }
}

@Composable
private fun MessageListItem(
  message: Message,
  isLast: Boolean,
  onDeleteClick: (Message) -> Unit,
) {
  Column {
    Card {
      ListItem(
        secondaryText = { Text(message.createTime.toString()) },
        icon = {
          Icon(
            imageVector = Icons.Default.Person,
            modifier = Modifier
              .background(
                MaterialTheme.colors.onBackground.copy(alpha = 0.5f),
                shape = CircleShape,
              )
              .size(48.dp),
          )
        },
        trailing = {
          IconButton(onClick = {
            onDeleteClick(message)
          }, content = {
            Icon(Icons.Default.Delete)
          })
        },
      ) {
        Text(buildAnnotatedString {
          withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
            append("${message.userName}: ")
          }
          append(message.message)
        })
      }
    }
  }
  if (!isLast) {
    Spacer(Modifier.height(1.dp))
  }
}
