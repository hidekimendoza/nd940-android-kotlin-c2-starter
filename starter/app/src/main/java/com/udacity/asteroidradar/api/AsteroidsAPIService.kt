package com.udacity.asteroidradar.api

import com.udacity.asteroidradar.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface AsteroidsAPIService {

    @GET("neo/rest/v1/feed")
    suspend fun getAsteroids(
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY_STRING): String

    @GET("neo/rest/v1/feed")
    suspend fun getAsteroidsByDate(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY_STRING): String
}
