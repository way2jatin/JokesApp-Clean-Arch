package com.unlimit.data.source.local

import com.unlimit.data.model.JokeModel
import com.unlimit.data.model.toData
import com.unlimit.data.model.toLocal
import com.unlimit.data.source.local.base.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class JokesLocalDataSourceImp(private val appDatabase: AppDatabase): JokesLocalDataSource {
    override suspend fun getJokes(): List<JokeModel> {
        return appDatabase.jokeDao.getAll().sortedByDescending { it.id }.take(10).map { it.toData() }
    }

    override suspend fun saveJokes(jokes: List<JokeModel>): List<JokeModel> = withContext(Dispatchers.IO) {
        appDatabase.jokeDao.insertAll(jokes.map { it.toLocal() })

        val jokeCount = appDatabase.jokeDao.getJokesCount()

        if (jokeCount > 10) {
            val jokesToDelete = appDatabase.jokeDao.getAll().take(jokeCount - 10)
            jokesToDelete.forEach {
                appDatabase.jokeDao.deleteAll(it.id)
            }
        }

        appDatabase.jokeDao.getAll().sortedByDescending { it.id }.take(10).map { it.toData() }
    }
}