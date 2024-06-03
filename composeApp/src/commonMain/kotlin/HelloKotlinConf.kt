package org.faye.kotlinconf

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HelloKotlinConf() {
    val word1 = "HELLO"
    val word2 = "KOTLIN"
    val word3 = "CONF"
    var flipState by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            flipState = !flipState
            delay(1000)
        }
    }
    
    Spacer(modifier = Modifier.size(16.dp))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (letter in word1) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = letter.toString(),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (letter in word2) {
                val rotationY by animateFloatAsState(
                    targetValue = if (flipState) 180f else 0f,
                    animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
                )

                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .background(Color(0xFFAC00DE))
                        .graphicsLayer {
                            this.rotationY = rotationY
                            cameraDistance = 8 * density
                        }
                        .drawWithCache {
                            onDrawWithContent {
                                drawContent()
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = letter.toString(),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
        
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (letter in word3) {
                val rotationY by animateFloatAsState(
                    targetValue = if (flipState) 180f else 0f,
                    animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
                )

                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .background(Color(0xFFAC00DE))
                        .graphicsLayer {
                            this.rotationY = rotationY
                            cameraDistance = 8 * density
                        }
                        .drawWithCache {
                            onDrawWithContent {
                                drawContent()
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = letter.toString(),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
        
    }
}

@Composable
@Preview
fun PreviewOverlappingDemo() {
    HelloKotlinConf()
}
