package com.example.dev.network

import com.example.dev.model.Joke
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import java.util.ArrayList

interface JokeApiService {
    @GET("jokes/ten")
    fun getJokes(): Observable<ArrayList<Joke>?>?
}