/*
 * Copyright (C) 2025 Hamza Gattal
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.hpoke.core.network.ktor

import com.example.hpoke.core.network.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.engine.okhttp.OkHttpConfig
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val BASE_URL = BuildConfig.BASE_URL

fun httpClient(): HttpClient =
    HttpClient(OkHttp) {
        okhttpClientConfig()
        defaultRequestConfig()
        contentNegotiationConfig()
        loggingConfig()
        timeoutConfig()
        responseValidationConfig()
    }

private fun HttpClientConfig<OkHttpConfig>.okhttpClientConfig() {
    followRedirects = true
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
            },
        )
    }
}

private fun HttpClientConfig<OkHttpConfig>.loggingConfig() {
    install(Logging) {
        logger = Logger.ANDROID
        level = LogLevel.INFO
    }
}

private fun HttpClientConfig<OkHttpConfig>.timeoutConfig() {
    install(HttpTimeout) {
        requestTimeoutMillis = 30000L
        connectTimeoutMillis = 15000L
        socketTimeoutMillis = 30000L
    }
}

private fun HttpClientConfig<OkHttpConfig>.responseValidationConfig() {
    expectSuccess = true
    HttpResponseValidator {
        handleResponseExceptionWithRequest { exception, _ ->
            when (exception) {
                is ClientRequestException -> {
                    throw ClientRequestException(
                        response = exception.response,
                        cachedResponseText = exception.response.bodyAsText(),
                    )
                }

                is ServerResponseException -> {
                    throw ServerResponseException(
                        response = exception.response,
                        cachedResponseText = exception.response.bodyAsText(),
                    )
                }

                is ResponseException -> {
                    throw ResponseException(
                        response = exception.response,
                        cachedResponseText = exception.response.bodyAsText(),
                    )
                }

                else -> {
                    throw exception
                }
            }
        }
    }
}
