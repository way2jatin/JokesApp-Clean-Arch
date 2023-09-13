package com.unlimit.data.source.remote

import com.unlimit.data.model.JokeModel
import com.unlimit.data.model.toData
import com.unlimit.data.source.remote.base.JokesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class JokesCloudDataSourceImp(private val api: JokesApi): JokesCloudDataSource {

    override suspend fun getJokes(): List<JokeModel> = withContext(Dispatchers.IO) {
        val response = api.getJoke()
        val resultsObject = response.body()
        val resultList = resultsObject?.let { listOf(it) } ?: emptyList()
        resultList.map { it.toData() }
    }
}