package com.udacity.asteroidradar.apipod

import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.PictureOfDay
import retrofit2.http.GET
import retrofit2.http.Query


interface PODAPIService {
    @GET("/planetary/apod")
    suspend fun getPOD(@Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY_STRING,
                       @Query("thumbs")thumbs: Boolean = true
    ): PictureOfDay
}