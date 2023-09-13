package com.unlimit.data.source.remote

import com.unlimit.data.model.JokeResponse
import com.unlimit.data.source.remote.base.JokesApi
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class JokesCloudDataSourceImpTest {

    @MockK
    lateinit var api: JokesApi

    private lateinit var dataSource: JokesCloudDataSourceImp

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        dataSource = JokesCloudDataSourceImp(api)
    }

    @Test
    fun `test getJokes returns expected data`() = runBlocking {
        // Given
        val mockResponse = JokeResponse(joke = "test joke")
        val mockRetrofitResponse = retrofit2.Response.success(mockResponse)
        
        coEvery { api.getJoke() } returns mockRetrofitResponse

        // When
        val result = dataSource.getJokes()

        // Then
        assertEquals(1, result.size)
        assertEquals("test joke", result.first().joke) // assuming JokeModel has a jokeText field
    }
}
