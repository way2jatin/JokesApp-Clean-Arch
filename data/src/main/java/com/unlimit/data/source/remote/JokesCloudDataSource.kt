package com.unlimit.data.source.remote

import com.unlimit.data.model.JokeModel

interface JokesCloudDataSource {

    suspend fun getJokes(): List<JokeModel>
}