package com.example.dev.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.dev.model.Joke
import com.example.dev.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class JokesViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val jokeSMutableList = MutableLiveData<ArrayList<Joke>>()
    val jokesFromApi: Unit
        get() {
            viewModelScope.launch {
                repository.getJokes()
                        .onStart { /* loading state */ }
                        .catch { exception -> println("Exception : $exception")}
                        .asLiveData().also {
                            jokeSMutableList.value = (it.value ?: arrayListOf<Joke>()) as ArrayList<Joke>?
                        }
            }
        }
    companion object {
        private const val TAG = "ViewModel"
    }
}


