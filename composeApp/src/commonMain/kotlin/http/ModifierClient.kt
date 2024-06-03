package org.faye.kotlinconf.http

import io.ktor.client.*
import io.ktor.client.engine.*

val client = HttpClient(getHttpEnginge())

expect fun getHttpEnginge(): HttpClientEngine