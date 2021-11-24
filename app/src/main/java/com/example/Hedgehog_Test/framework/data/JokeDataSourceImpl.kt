package com.example.Hedgehog_Test.framework.data

import android.util.Log
import com.example.Hedgehog_Test.BuildConfig
import com.example.Hedgehog_Test.business.data.JokeDataSource
import com.example.Hedgehog_Test.business.domain.Joke
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.util.concurrent.TimeUnit

object JokeDataSourceImpl: JokeDataSource {

    private const val CONNECT_TIMEOUT_SECONDS = 120L
    private const val READ_TIMEOUT_SECONDS = 120L

    override suspend fun getJokes(number: Int): List<Joke> {
        return try {
            jokesService.getRestaurantsByBranchIds(number).jokes
        } catch (e: Exception) {
            Log.w("", e)
            emptyList<Joke>()
        }
    }

    private val jokesService: JokesService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.icndb.com/")
            .client(
                provideOkHttpClient(
                    httpLoggingInterceptor = provideLoggingInterceptor(),
                    headerInterceptor = provideHeaderInterceptor()
                )
            )
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JokesService::class.java)
    }

    private fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: HeaderInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(headerInterceptor)
            .connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        return logging
    }


    fun provideHeaderInterceptor(): HeaderInterceptor {
        return HeaderInterceptor()
    }
}