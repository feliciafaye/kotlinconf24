package org.faye.kotlinconf.local.node

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.currentValueOf
import org.faye.kotlinconf.local.value.LocalBackgroundColor

internal fun Modifier.localBackground(): Modifier {
	return this then BackgroundElement
}

private class BackgroundNode:
	DrawModifierNode,
	Modifier.Node(),
	CompositionLocalConsumerModifierNode {
	override fun ContentDrawScope.draw() {
		val color = currentValueOf(LocalBackgroundColor)
		drawRect(color)
	}
}

private data object BackgroundElement : ModifierNodeElement<BackgroundNode>() {
	override fun create(): BackgroundNode =
		BackgroundNode()

	override fun update(node: BackgroundNode) {
		// nothing to do
	}
}

