package com.example.Hedgehog_Test.business.domain

import com.google.gson.annotations.SerializedName

class JokeResponse {

    @SerializedName("type")
    var type: String = ""

    @SerializedName("value")
    var jokes: List<Joke> = emptyList()
}