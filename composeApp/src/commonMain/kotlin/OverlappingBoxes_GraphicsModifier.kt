package org.faye.kotlinconf

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import kotlinconf2024.composeapp.generated.resources.Res
import kotlinconf2024.composeapp.generated.resources.image1
import org.jetbrains.compose.resources.painterResource

@Composable
fun OverlappingBoxes() {
    Column(
        modifier = Modifier
            .size(height = 200.dp, width = 100.dp) // Use .fillMaxSize to make it visible
            .background(Color.Black)
    ) {
        // Image that will be moved out of bounds
        Image(
            painter = painterResource(Res.drawable.image1),
            contentDescription = "Image 1",
            modifier = Modifier
                .size(100.dp)
                .background(Color.Red)
                .rotate(9F)
                .graphicsLayer(
                    translationX = 300f, // Translate far enough to be out of bounds
                    translationY = 300f  // Translate far enough to be out of bounds
                )
        )

        // Reference image to indicate the original bounds
        Image(
            painter = painterResource(Res.drawable.image1),
            contentDescription = "Image 1",
            modifier = Modifier
                .size(100.dp)
                .background(Color.Blue)
        )
    }
}
