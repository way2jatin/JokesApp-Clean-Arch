package com.unlimit.domain.repository

import com.unlimit.domain.model.Joke

interface JokesRepository {
    suspend fun getJokes(networkAvailability : Boolean): List<Joke>?
}