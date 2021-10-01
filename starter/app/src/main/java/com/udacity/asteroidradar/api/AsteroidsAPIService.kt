package com.udacity.asteroidradar.network

import retrofit2.http.GET
import retrofit2.http.Query

private const val API_NASA_KEY = "dyg5rjeC1ly30KsWGB2JQG8ApN4yGpa0hMZUdVcg"

interface AsteroidsAPIService {
    @GET("neo/rest/v1/feed")
    suspend fun getAsteroids(@Query("api_key") apiKey: String = API_NASA_KEY): String
}
