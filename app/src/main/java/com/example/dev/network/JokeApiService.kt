package com.example.dev.network

import com.example.dev.model.Joke
import retrofit2.http.GET
import java.util.ArrayList

interface JokeApiService {
    @GET("jokes/ten")
    suspend fun getJokes(): ArrayList<Joke>
}