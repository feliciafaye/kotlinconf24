package org.faye.kotlinconf

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

fun Modifier.glitch(): Modifier = composed {
    val infiniteTransition = rememberInfiniteTransition()
    val glitchShift = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 100f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 300
                0f at 0 using LinearEasing
                100f at 150 using LinearEasing
                0f at 300 using LinearEasing
            },
            repeatMode = RepeatMode.Restart
        )
    )

    this.drawBehind {
        repeat(5) {
            val randomHeight = Random.nextFloat() * size.height / 10
            val randomY = Random.nextFloat() * size.height
            val randomWidthFactor = Random.nextFloat()

            drawRect(
                color = if (Random.nextBoolean()) Color.Green.copy(alpha = 0.5f) else Color.Red.copy(alpha = 0.5f),
                topLeft = androidx.compose.ui.geometry.Offset(
                    (size.width * glitchShift.value / 100 * randomWidthFactor),
                    randomY
                ),
                size = androidx.compose.ui.geometry.Size(size.width * 0.2f, randomHeight),
                style = Fill
            )
        }
    }
}

@Composable
fun GlitchEffect() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .glitch(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .padding(16.dp),
            text = "GLITCH",
            style = TextStyle.Default.copy(
                color = Color.White,
                fontSize = 30.sp
            )
        )
    }
}

