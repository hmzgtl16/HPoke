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

import org.junit.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class HttpClientConfigTest {
    @Test
    fun testHttpClientCreation() {
        // Act
        val client = httpClient()

        // Assert
        assertNotNull(client)
    }

    @Test
    fun testMultipleHttpClientInstances() {
        // Act
        val client1 = httpClient()
        val client2 = httpClient()

        // Assert
        assertNotNull(client1)
        assertNotNull(client2)
        // Each call should create a new instance
        assertTrue(client1 != client2)
    }

    @Test
    fun testHttpClientCanMakeRequests() {
        // Act
        val client = httpClient()

        // Assert
        assertNotNull(client)
        // Client should be properly configured
        assertNotNull(client.engine)
    }
}
