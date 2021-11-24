package com.example.Hedgehog_Test.framework.data

import com.example.Hedgehog_Test.business.domain.JokeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface JokesService {

    @GET("jokes/random/{number}")
    suspend fun getRestaurantsByBranchIds(@Path("number") number: Int): JokeResponse
}