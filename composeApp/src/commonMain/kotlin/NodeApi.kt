package org.faye.kotlinconf

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


fun Modifier.customPaddingAndBackground(padding: Dp, bgColor: Color): Modifier = this
    .padding(padding)
    .background(bgColor)


fun Modifier.customPaddingAndBackgroundNode(padding: Dp, bgColor: Color): Modifier =
    this
        .padding(padding)
        .then(
            Modifier.drawWithContent {
                drawContent()
                drawRect(color = bgColor, size = size)
            }
        )

@Composable
fun Modifier.fade(animateAlpha: Boolean): Modifier {
    val alpha by animateFloatAsState(if (animateAlpha) 0.3f else 1.0f)
    return this then Modifier.graphicsLayer { this.alpha = alpha }
}

// Modifier factory
fun Modifier.blacken(color: Color = Color.Black) = this then RectElement(color)

// ModifierNodeElement
private data class RectElement(val color: Color) : ModifierNodeElement<RectNode>() {
    override fun create() = RectNode(color)

    override fun update(node: RectNode) {
        node.color = color
    }
}

// Modifier.Node
private class RectNode(var color: Color) : DrawModifierNode, Modifier.Node() {
    override fun ContentDrawScope.draw() {
        drawRect(color)
    }
}

@Composable
fun Modifier.alphaBackground(): Modifier {
    val color = LocalContentColor.current
    return this then Modifier.background(color.copy(alpha = 0.3f))
}

@Composable
fun NodeApi() {
    CompositionLocalProvider(LocalContentColor provides Color.Green) {
        val backgroundModifier = Modifier.alphaBackground().size(50.dp)
        CompositionLocalProvider(LocalContentColor provides Color.Red) {
            Box(modifier = backgroundModifier)
        }
    }
}

@Composable
fun Demo5_Rect() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Some secret text", modifier = Modifier.blacken().fade(animateAlpha = true))
    }
}
