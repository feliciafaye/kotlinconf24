package org.faye.kotlinconf

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import com.kmpalette.color
import com.kmpalette.rememberPaletteState
import kotlinconf2024.composeapp.generated.resources.*
import kotlinconf2024.composeapp.generated.resources.Res
import kotlinconf2024.composeapp.generated.resources.image1
import kotlinconf2024.composeapp.generated.resources.image2
import kotlinconf2024.composeapp.generated.resources.image3
import org.jetbrains.compose.resources.imageResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.vectorResource

@Composable
fun CuttlefishCamouflage() {
    val images = listOf(Res.drawable.image1, Res.drawable.image2, Res.drawable.image3)
    var imageIndex by remember { mutableStateOf(0) }
    var offsetX by remember { mutableStateOf(0f) }
    val threshold = 100f

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .draggable(
                orientation = Orientation.Horizontal,
                state = rememberDraggableState { delta ->
                    offsetX += delta
                },
                onDragStopped = {
                    when {
                        offsetX > threshold -> {
                            imageIndex = (imageIndex - 1 + images.size) % images.size
                        }

                        offsetX < -threshold -> {
                            imageIndex = (imageIndex + 1) % images.size
                        }
                    }
                    offsetX = 0f
                    println("Image Index: $imageIndex")
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        val resId = images[imageIndex]
        val imagePainter = painterResource(resId)
        Image(
            painter = imagePainter,
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        val paletteState = rememberPaletteState()
        val bitmap = imageResource(images[imageIndex])
        LaunchedEffect(imageIndex) {
            paletteState.generate(bitmap)
        }

        val transition = rememberInfiniteTransition()
        val colorIndex by transition.animateValue(
            initialValue = 0,
            targetValue = 2,
            typeConverter = Int.VectorConverter,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 1200,
                    easing = FastOutSlowInEasing
                ),
                repeatMode = RepeatMode.Reverse
            )
        )

        val colorList = listOf(
            paletteState.palette?.dominantSwatch?.color ?: Color.Transparent,
            paletteState.palette?.vibrantSwatch?.color ?: Color.Transparent
        )
        val gradientColors = remember(colorIndex) {
            when (colorIndex) {
                0 -> colorList
                1 -> colorList.reversed()
                else -> listOf(Color.Transparent, Color.Transparent)
            }
        }
        val brushGradient = Brush.horizontalGradient(
            colors = gradientColors,
            tileMode = TileMode.Mirror
        )

        Icon(
            imageVector = vectorResource(Res.drawable.cuttlefish),
            tint = Color(0xFFAC00DE),
            contentDescription = "cuttlefish",
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(alpha = 0.99f)
                .drawWithCache {
                    onDrawWithContent {
                        drawContent()
                        drawRect(brushGradient, blendMode = BlendMode.SrcAtop)
                    }
                }
        )
    }
}
