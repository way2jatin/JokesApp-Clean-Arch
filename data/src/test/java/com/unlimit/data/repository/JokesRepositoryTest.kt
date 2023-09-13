package com.unlimit.data.repository

import com.unlimit.data.model.JokeModel
import com.unlimit.data.model.toDomain
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test

class JokesRepositoryTest {

    @MockK
    lateinit var jokesRepository: JokesRepositoryImp

    @Before
    fun setUp() {
        MockKAnnotations.init(this) //for initialization
    }

    @Test
    fun testOnlineJokesData() = runBlocking {
        val jokes = mockk<List<JokeModel>>(relaxed = true)
        coEvery {
            jokesRepository.getJokes(true)
        } returns (jokes.map { it.toDomain() })

        val result = jokesRepository.getJokes(true)
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$jokes] must be matches on each other!",
            result,
            CoreMatchers.`is`(jokes)
        )
    }

    @Test
    fun testOfflineJokesData() = runBlocking {
        val jokes = mockk<List<JokeModel>>(relaxed = true)
        coEvery {
            jokesRepository.getJokes(false)
        } returns (jokes.map { it.toDomain() })

        val result = jokesRepository.getJokes(false)
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$jokes] must be matches on each other!",
            result,
            CoreMatchers.`is`(jokes)
        )
    }
}