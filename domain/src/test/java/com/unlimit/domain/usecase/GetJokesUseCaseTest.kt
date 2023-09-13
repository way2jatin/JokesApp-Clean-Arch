package com.unlimit.domain.usecase

import com.unlimit.domain.model.Joke
import com.unlimit.domain.repository.JokesRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetJokesUseCaseTest {

    private lateinit var usecase: GetJokesUseCase
    private val mockRepository: JokesRepository = mockk()
    private val mockResult = mockk<List<Joke>>(relaxed = true)

    @Before
    fun setUp() {
        usecase = GetJokesUseCase(mockRepository)
    }

    @Test
    fun testGetOnlineJokesUseCase() {
        val result: List<Joke>?

        coEvery {
            mockRepository.getJokes(true)
        } returns (mockResult)

        runBlocking { result = usecase.run(true) }

        coVerify { mockRepository.getJokes(true) }
        Assert.assertEquals(result, mockResult)
    }


    @Test
    fun testGetOfflineJokesUseCase() {
        val result: List<Joke>?

        coEvery {
            mockRepository.getJokes(false)
        } returns (mockResult)

        runBlocking { result = usecase.run(false) }

        coVerify { mockRepository.getJokes(false) }
        Assert.assertEquals(result, mockResult)
    }
}