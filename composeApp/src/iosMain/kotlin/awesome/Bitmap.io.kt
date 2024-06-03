package org.faye.kotlinconf.awesome

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asComposeImageBitmap
import io.ktor.util.*
import io.ktor.utils.io.*
import org.jetbrains.skia.Bitmap
import org.jetbrains.skia.Image

actual suspend fun ByteReadChannel.toBitmap(): ImageBitmap? {
	val bitmap = Bitmap.makeFromImage(Image.makeFromEncoded(toByteArray()))
	return bitmap.asComposeImageBitmap()
}
	