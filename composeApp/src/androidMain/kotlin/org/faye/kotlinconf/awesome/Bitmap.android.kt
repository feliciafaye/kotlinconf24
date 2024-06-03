package org.faye.kotlinconf.awesome

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import io.ktor.utils.io.*
import io.ktor.utils.io.jvm.javaio.*

actual suspend fun ByteReadChannel.toBitmap(): ImageBitmap? =
	toInputStream().use {
		BitmapFactory.decodeStream(it).asImageBitmap()
	}
