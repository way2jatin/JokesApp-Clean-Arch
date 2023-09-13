package com.unlimit.data.source.local

import com.unlimit.data.model.JokeModel

interface JokesLocalDataSource {

    suspend fun getJokes(): List<JokeModel>

    suspend fun saveJokes(list: List<JokeModel>): List<JokeModel>

}