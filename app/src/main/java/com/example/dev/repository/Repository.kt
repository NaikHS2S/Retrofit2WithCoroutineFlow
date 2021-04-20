package com.example.dev.repository

import com.example.dev.model.Joke
import com.example.dev.network.JokeApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: JokeApiService) {
    fun getJokes(): Flow<List<Unit>>
    {
        return flow {
            val jokesList = apiService.getJokes().
            map {
                fromApi -> println("response from api $fromApi")
            }
            // Emit the list to the stream
            emit(jokesList)
        }.flowOn(Dispatchers.IO)// Use the IO thread for this Flow
    }
}