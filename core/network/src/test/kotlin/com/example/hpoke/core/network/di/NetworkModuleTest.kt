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

package com.example.hpoke.core.network.di

import com.example.hpoke.core.network.api.PokemonApi
import com.example.hpoke.core.network.api.PokemonApiImpl
import io.ktor.client.HttpClient
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.java.KoinJavaComponent.inject
import kotlin.test.assertNotNull
import kotlin.test.assertSame
import kotlin.test.assertTrue

class NetworkModuleTest {
    @Before
    fun setup() {
        startKoin {
            modules(networkModule)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun testHttpClientInjection() {
        // Act
        val httpClient: HttpClient by inject(HttpClient::class.java)

        // Assert
        assertNotNull(httpClient)
    }

    @Test
    fun testPokemonApiInjection() {
        // Act
        val pokemonApi: PokemonApi by inject(PokemonApi::class.java)

        // Assert
        assertNotNull(pokemonApi)
        assertTrue(pokemonApi is PokemonApiImpl)
    }

    @Test
    fun testHttpClientIsSingleton() {
        // Act
        val httpClient1: HttpClient by inject(HttpClient::class.java)
        val httpClient2: HttpClient by inject(HttpClient::class.java)

        // Assert
        assertSame(httpClient1, httpClient2)
    }

    @Test
    fun testPokemonApiIsSingleton() {
        // Act
        val api1: PokemonApi by inject(PokemonApi::class.java)
        val api2: PokemonApi by inject(PokemonApi::class.java)

        // Assert
        assertSame(api1, api2)
    }
}
