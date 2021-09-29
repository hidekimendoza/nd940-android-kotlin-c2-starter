package com.udacity.asteroidradar.api

import com.udacity.asteroidradar.network.AsteroidsAPIService
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val API_NASA_BASE_URL = "https://api.nasa.gov/"

object RetrofitAsteroidsInstance {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(API_NASA_BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

    val api: AsteroidsAPIService by lazy {
        retrofit.create(AsteroidsAPIService::class.java)
    }

}