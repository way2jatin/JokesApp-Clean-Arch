package com.unlimit.data.model

import org.junit.Assert.assertEquals
import org.junit.Test

class JokeResponseTest {

    /**
     * "joke": "Chuck Norris can write infinite recursion functions and have them return."
     */

    private val jokeResponse =  JokeResponse(
        joke = "Chuck Norris can write infinite recursion functions and have them return."
    )

    @Test
    fun testJokeResponse() {
        // Create an instance of JokeResponse
        val jokeResponse = JokeResponse(
            joke = "Chuck Norris can write infinite recursion functions and have them return."
        )

        // Verify the properties of the JokeResponse object
        assertEquals(
            "Chuck Norris can write infinite recursion functions and have them return.",
            jokeResponse.joke
        )
    }

}