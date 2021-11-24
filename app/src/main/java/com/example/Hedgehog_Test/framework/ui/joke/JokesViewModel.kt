package com.example.Hedgehog_Test.framework.ui.joke

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Hedgehog_Test.business.domain.Joke
import com.example.Hedgehog_Test.framework.data.JokeDataSourceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JokesViewModel : ViewModel() {

    private val _jokeList = MutableLiveData<List<Joke>>()
    val jokeList: LiveData<List<Joke>> = _jokeList

    private val DEFAULT_JOKES_NUMBER = 5

    fun fetchJokes(){
        viewModelScope.launch(Dispatchers.IO) {
            val jokes = JokeDataSourceImpl.getJokes(DEFAULT_JOKES_NUMBER)
            _jokeList.postValue(jokes)
        }
    }

    fun loadJokesByNumber(number: Int) {
        if (number <= 0) {
            _jokeList.postValue(emptyList())
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            val jokes = JokeDataSourceImpl.getJokes(number)
            _jokeList.postValue(jokes)
        }
    }

}