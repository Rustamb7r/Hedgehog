package com.example.Hedgehog_Test.business.domain

import com.google.gson.annotations.SerializedName


data class Joke(
    @SerializedName("id")
    val id: String,
    @SerializedName("joke")
    val joke: String
)

