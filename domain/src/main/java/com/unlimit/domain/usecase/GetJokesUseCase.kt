package com.unlimit.domain.usecase

import com.unlimit.domain.base.SingleUseCase
import com.unlimit.domain.model.Joke
import com.unlimit.domain.repository.JokesRepository

class GetJokesUseCase(private val jokesRepository: JokesRepository): SingleUseCase<List<Joke>?, Boolean>() {
    override suspend fun run(params: Boolean?): List<Joke>? {
        return params?.let { jokesRepository.getJokes(it) }
    }
}