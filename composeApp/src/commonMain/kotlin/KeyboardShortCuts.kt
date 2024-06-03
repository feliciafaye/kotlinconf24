package org.faye.kotlinconf

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.unit.dp


@Composable
fun KeyboardShortCuts() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        Arrangement.spacedBy(16.dp)
    ) {
        var savedCharsCount by remember { mutableStateOf(0) }
        var discardedCharsCount by remember { mutableStateOf(0) }
        var message by remember { mutableStateOf("") }

        Text("Saved chars: $savedCharsCount", color = Color.White)
        Text("Discarded chars: $discardedCharsCount", color = Color.White)

        TextField(
            value = message,
            onValueChange = { message = it },
            modifier = Modifier.onPreviewKeyEvent {
                when {
                    (it.isCtrlPressed && it.key == Key.Minus && it.type == KeyEventType.KeyUp) -> {
                        discardedCharsCount += message.length - 1
                        message = ""
                        true
                    }

                    (it.isCtrlPressed && it.key == Key.Equals && it.type == KeyEventType.KeyUp) -> {
                        savedCharsCount += message.length - 1
                        message = ""
                        true
                    }

                    else -> false
                }
            }
        )
    }
}

// todo meike use control und fragezeichentaste sonst nichts
// todo meike use control und taste rechts neben fragezeichen sonst nichts
