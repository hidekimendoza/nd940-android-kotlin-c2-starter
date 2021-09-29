package com.udacity.asteroidradar.apipod

import com.udacity.asteroidradar.PictureOfDay
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_NASA_KEY = "dyg5rjeC1ly30KsWGB2JQG8ApN4yGpa0hMZUdVcg"

interface PODAPIService {
    @GET("/planetary/apod")
    suspend fun getPOD(@Query("api_key") apiKey: String = API_NASA_KEY,
    @Query("thumbs")thumbs: Boolean = true
    ): PictureOfDay
}