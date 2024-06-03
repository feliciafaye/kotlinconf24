package org.faye.kotlinconf.http

import io.ktor.client.engine.*
import io.ktor.client.engine.darwin.*

actual fun getHttpEnginge(): HttpClientEngine =
	Darwin.create()

