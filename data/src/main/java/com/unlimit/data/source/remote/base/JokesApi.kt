package com.unlimit.data.source.remote.base

import com.unlimit.data.model.JokeResponse
import retrofit2.Response
import retrofit2.http.GET

interface JokesApi {

    @GET("/api?format=json")
    suspend fun getJoke(): Response<JokeResponse>
}