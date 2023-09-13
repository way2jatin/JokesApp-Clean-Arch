package com.unlimit.data.repository

import com.unlimit.data.model.toDomain
import com.unlimit.data.source.local.JokesLocalDataSource
import com.unlimit.data.source.remote.JokesCloudDataSource
import com.unlimit.domain.model.Joke
import com.unlimit.domain.repository.JokesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class JokesRepositoryImp(
    private val jokesCloudDataSource: JokesCloudDataSource,
    private val jokesLocalDataSource: JokesLocalDataSource
): JokesRepository {
    override suspend fun getJokes(networkAvailability: Boolean): List<Joke> = withContext(Dispatchers.IO)  {
        if (networkAvailability) {
            // If the network is available, fetch jokes from the remote source.
            getJokesFromRemote()
        } else {
            // If the network is not available, fetch jokes from a local source or cached data.
            val localJokes = getJokesFromLocal()

            if (localJokes.isEmpty()) {
                // If there are no cached jokes, you can handle the situation accordingly,
                // e.g., show an error message or load default jokes.
                emptyList<Joke>()
            }

            localJokes
        }

    }

    private suspend fun getJokesFromRemote(): List<Joke> = withContext(Dispatchers.IO) {
        val jokesFromRemote = jokesCloudDataSource.getJokes()
        if (jokesFromRemote.isNotEmpty()) {
            jokesLocalDataSource.saveJokes(jokesFromRemote).map { it.toDomain() }
        } else {
            getJokesFromLocal()
        }
    }

    private suspend fun getJokesFromLocal(): List<Joke> = withContext(Dispatchers.IO) {
        jokesLocalDataSource.getJokes().map { it.toDomain() }
    }
}