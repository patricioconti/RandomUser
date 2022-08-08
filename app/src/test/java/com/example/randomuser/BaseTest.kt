package com.example.randomuser

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source

open class BaseTest {

    //This object will intercept our network requests,
    val mockWebServer = MockWebServer()
// The enqueue() function in this class parses the data from the random_user.json
// file that you created so that it can be used in a test that you will write later on.

    fun enqueue(fileName: String) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val buffer = inputStream.source().buffer()

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(buffer.readString(Charsets.UTF_8))
        )
    }
}