package com.example.hpoke.core.network.ktor

import com.example.hpoke.core.network.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.engine.okhttp.OkHttpConfig
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val BASE_URL = BuildConfig.BASE_URL

fun httpClient(): HttpClient = HttpClient(OkHttp) {
    defaultRequestConfig()
    contentNegotiationConfig()
    loggingConfig()
}

private fun HttpClientConfig<OkHttpConfig>.defaultRequestConfig() {
    install(DefaultRequest) {
        url(BASE_URL)
    }
}

private fun HttpClientConfig<OkHttpConfig>.contentNegotiationConfig() {
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            }
        )
    }
}

private fun HttpClientConfig<OkHttpConfig>.loggingConfig() {
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
    }
}