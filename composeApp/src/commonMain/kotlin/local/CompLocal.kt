package org.faye.kotlinconf.local

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.faye.kotlinconf.local.node.localBackground as localBackgroundNode
import org.faye.kotlinconf.local.factory.localBackground as localBackgroundFactory
import org.faye.kotlinconf.local.value.LocalBackgroundColor


@Composable
fun CompLocal() {
    var useNode by remember { mutableStateOf(false) }

    CompositionLocalProvider(LocalBackgroundColor provides Color.Green) {
        val withBackground = if (useNode) {
            Modifier.localBackgroundNode()
        } else {
            Modifier.localBackgroundFactory()
        }
        CompositionLocalProvider(LocalBackgroundColor provides Color.Red) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Box(
                    modifier = withBackground
                        .size(300.dp)
                )
                Spacer(Modifier.size(32.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Use Node API")
                    Spacer(Modifier.size(8.dp))
                    Switch(
                        checked = useNode,
                        onCheckedChange = { useNode = it }
                    )
                }
            }
        }
    }
}

