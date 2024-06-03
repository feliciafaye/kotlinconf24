package org.faye.kotlinconf.awesome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinconf2024.composeapp.generated.resources.Res
import kotlinconf2024.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun AwesomeScreen() {
    var showContent by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val buttonModifier = if (showContent) {
            Modifier
        } else {
            Modifier.cycleFade()
        }
        Button(
            modifier = buttonModifier,
            onClick = { showContent = !showContent }
        ) {
            Text("Click me!")
        }
        AnimatedVisibility(showContent) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(500.dp)
                        .lazyBackgroundImage(
                            "https://picsum.photos/500",
                            painterResource(Res.drawable.compose_multiplatform)
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "Multiplatform Compose",
                        color = Color.LightGray,
                        fontSize = 28.sp
                    )
                }
            }
        }
    }
}
