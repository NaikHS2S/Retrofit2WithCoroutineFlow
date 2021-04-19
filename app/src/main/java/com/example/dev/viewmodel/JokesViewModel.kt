package com.example.dev.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dev.model.Joke
import com.example.dev.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class JokesViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val jokeSMutableList = MutableLiveData<ArrayList<Joke>>()
    val jokesFromApi: Unit
        get() {
            repository.getJokes()?.subscribeOn(Schedulers.io())
                    ?.map { response -> Log.i(TAG, "Response: ${response.toString()}")
                        response
                    }
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe(
                            { result -> jokeSMutableList.setValue(result)},
                            {  error-> Log.e(TAG, "getJokes: " + error.localizedMessage)})

        }

    companion object {
        private const val TAG = "ViewModel"
    }

}