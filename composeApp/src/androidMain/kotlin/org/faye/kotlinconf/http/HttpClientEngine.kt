package org.faye.kotlinconf.http


import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*

actual fun getHttpEnginge(): HttpClientEngine =
	OkHttp.create()