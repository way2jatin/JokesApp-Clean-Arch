package com.unlimit.data.source.local

import com.unlimit.data.model.JokeModel
import com.unlimit.data.model.toData
import com.unlimit.data.source.local.base.AppDatabase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class JokesLocalDataSourceImpTest {

    private lateinit var localDataSource: JokesLocalDataSourceImp
    private val mockResult = mockk<List<JokeModel>>(relaxed = true)
    private val mockDatabase : AppDatabase = mockk()

    @Before
    fun setUp() {
        localDataSource = JokesLocalDataSourceImp(mockDatabase)
    }

    @Test
    fun testGetJokesLocalDataSource() {
        val result : List<JokeModel>?
        coEvery {  mockDatabase.jokeDao.getAll().map { it.toData() } } returns mockResult

        runBlocking {  result = localDataSource.getJokes() }

        coVerify { mockDatabase.jokeDao.getAll()  }

        Assert.assertEquals(result, mockResult)
    }

}