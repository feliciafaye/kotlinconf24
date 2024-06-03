package org.faye.kotlinconf.awesome

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import org.faye.kotlinconf.http.client
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@Composable
fun Modifier.cycleFade(cicleTime: Long = 1_000): Modifier {
	var gone by remember { mutableStateOf(false) }
	val alpha by animateFloatAsState(if (gone) 0f else 1f, animationSpec = tween(500))

	LaunchedEffect(Unit) {
		while (isActive) {
			gone = !gone
			delay(cicleTime)
		}
	}

	return this then Modifier.graphicsLayer(alpha = alpha)
}
 
@Composable
fun Modifier.lazyBackgroundImage(
	url: String,
	placeHolder: Painter? = null,
	backgroundAlpha: Float = .6f
): Modifier {
	var image by remember { mutableStateOf<Painter?>(null) }

	LaunchedEffect(Unit) {
		val response = client.get(url)
		if (response.status == HttpStatusCode.OK) {
			val channel = response.bodyAsChannel()
			delay(2_000)
			image = channel.toBitmap()?.let { BitmapPainter(it) }
		}
	}

	return this then
			Modifier.drawBehind {
				val usableImage = image ?: placeHolder ?: return@drawBehind
				with(usableImage) {
					draw(size = size, alpha = backgroundAlpha)
				}
			}
}

expect suspend fun ByteReadChannel.toBitmap(): ImageBitmap?
