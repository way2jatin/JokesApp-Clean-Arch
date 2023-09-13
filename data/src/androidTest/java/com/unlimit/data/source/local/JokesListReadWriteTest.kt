package com.unlimit.data.source.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.unlimit.data.model.JokeEntity
import com.unlimit.data.source.local.base.AppDatabase
import com.unlimit.data.source.local.base.dao.JokeDao
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class JokesListReadWriteTest {

    private lateinit var jokeDao: JokeDao
    private lateinit var database : AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        jokeDao = database.jokeDao
    }

    @Test
    @Throws(Exception::class)
    fun testWriteJokeListAndRead() {
        val mockedJoke = mockk<JokeEntity>(relaxed = true)
        val jokeList = mutableListOf(mockedJoke)

        runBlocking {
            jokeDao.insertAll(jokeList)
        }

        runBlocking {
            val loadedJokeList = jokeDao.getAll()
            MatcherAssert.assertThat(loadedJokeList.size, CoreMatchers.equalTo(1))
        }
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }
}