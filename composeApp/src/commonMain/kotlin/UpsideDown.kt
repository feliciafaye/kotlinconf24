package org.faye.kotlinconf

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale

@Composable
fun UpsideDown() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black),
        contentAlignment = Alignment.Center
    )
    {
        Text(
            text = "Cuttlefish".capitalize(Locale.current),
            color = Color.White,
            modifier = Modifier.upsideDown()
        )
    }
}

class UpsideDownModifier : DrawModifier {
    override fun ContentDrawScope.draw() {
        scale(scaleX = 1f, scaleY = -1f) {
            this@draw.drawContent()
        }
    }
}

fun Modifier.upsideDown() = this.then(UpsideDownModifier())

