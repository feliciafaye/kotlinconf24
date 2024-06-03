package org.faye.kotlinconf

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times

@Composable
fun GrowingBox() {
    var clicks by remember { mutableStateOf(0) }
    val minWidth = 100.dp
    val maxWidth = 300.dp
    val stepWidth = 20.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Box(
            modifier = Modifier
                .size(300.dp)
                .padding(16.dp)
                .background(Color.Red)
        ) {
            Text(
                text = "Nothing todo",
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp)
            )
        }

        Box(
            modifier = Modifier
                .size(300.dp)
                .padding(16.dp)
                .clickable {
                    // Increase click count, width increases with each click
                    clicks = (clicks + 1) % ((maxWidth - minWidth) / stepWidth).toInt()
                }
                .background(Color.Blue)
                .layout { measurable, constraints ->
                    val targetWidth = minWidth + (clicks * stepWidth).coerceIn(0.dp, maxWidth - minWidth)
                    val newConstraints =
                        constraints.copy(minWidth = targetWidth.roundToPx(), maxWidth = targetWidth.roundToPx())
                    val placeable = measurable.measure(newConstraints)

                    layout(placeable.width, placeable.height) {
                        placeable.placeRelative(0, 0)
                    }
                }
        ) {
            Text(
                text = "Click me!",
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
