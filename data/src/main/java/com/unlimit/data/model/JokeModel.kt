package com.unlimit.data.model

import com.squareup.moshi.JsonClass
import com.unlimit.domain.model.Joke

@JsonClass(generateAdapter = true)
data class JokeModel(
    val id: Long = 0,
    val joke: String
)

fun JokeModel.toDomain() = Joke(
    id = id,
    joke = joke
)

fun JokeModel.toLocal() = JokeEntity (
    joke = joke
)
