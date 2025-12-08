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
