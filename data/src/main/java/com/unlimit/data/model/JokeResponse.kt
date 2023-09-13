package com.unlimit.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JokeResponse(
    var joke: String
)

fun JokeResponse.toData() = JokeModel(
    joke = joke
)

