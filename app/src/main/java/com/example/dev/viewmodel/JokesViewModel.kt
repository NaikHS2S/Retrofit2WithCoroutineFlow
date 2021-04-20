package com.example.dev.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dev.model.Joke
import com.example.dev.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class JokesViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val jokeSMutableList = MutableLiveData<ArrayList<Joke>>()
    fun jokesFromApi() {

            viewModelScope.launch(Dispatchers.IO) {
                repository.getJokes()
                        .onStart { /* loading state */ }
                        .catch { exception -> println("Exception : $exception") }
                        .collectLatest {
                            withContext(Dispatchers.Main) {
                                jokeSMutableList.value = it as ArrayList<Joke>?
                            }
                        }
            }
    }

    companion object {
        private const val TAG = "ViewModel"
    }
}


