package com.example.Hedgehog_Test.business.data

import com.example.Hedgehog_Test.business.domain.Joke

interface JokeDataSource {

    suspend fun getJokes(number: Int): List<Joke>
}