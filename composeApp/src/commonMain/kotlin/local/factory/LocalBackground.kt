package org.faye.kotlinconf.local.factory

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.faye.kotlinconf.local.value.LocalBackgroundColor

@Composable
internal fun Modifier.localBackground(): Modifier {
	val color = LocalBackgroundColor.current
	return background(color = color)
}

