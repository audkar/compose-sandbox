package app.nameplaceholder.common

import androidx.compose.runtime.Composable

expect fun getPlatformName(): String


@Composable
expect fun androidx.compose.ui.Modifier.platformShortcuts(callback: () -> Unit): androidx.compose.ui.Modifier
