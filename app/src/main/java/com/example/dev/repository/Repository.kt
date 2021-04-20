package com.example.dev.repository

import com.example.dev.model.Joke
import com.example.dev.network.JokeApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: JokeApiService) {
    fun getJokes(): Flow<List<Joke>> {
        return flow {
            val jokesList = apiService.getJokes()
                    .map { fromApi -> fromApi}
                    .filter { it.id > 0 }
                    .take(8)
            emit(jokesList)
        }.flowOn(Dispatchers.IO)
    }
}