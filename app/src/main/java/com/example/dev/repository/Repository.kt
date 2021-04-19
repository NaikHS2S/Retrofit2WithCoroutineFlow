package com.example.dev.repository

import androidx.lifecycle.LiveData
import com.example.dev.model.Joke
import com.example.dev.network.JokeApiService
import io.reactivex.rxjava3.core.Observable
import java.util.ArrayList
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: JokeApiService) {
    fun getJokes(): Observable<ArrayList<Joke>?>? = apiService.getJokes()

}