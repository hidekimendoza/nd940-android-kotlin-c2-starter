package com.udacity.asteroidradar.apipod


import com.squareup.moshi.Json
import com.udacity.asteroidradar.PictureOfTheDay
import com.udacity.asteroidradar.database.DatabasePictureOfTheDay

data class PODDataTransferObject(
    @Json(name = "media_type") val mediaType: String,
    val title: String,
    val url: String,
    @Json(name = "thumbnail_url") val thumbnailUrl: String? = null
)


fun PODDataTransferObject.toDatabaseModel(): DatabasePictureOfTheDay {
    return DatabasePictureOfTheDay(
        id = 0,
        mediaType = this.mediaType,
        title = this.title,
        url = this.url,
        thumbnailUrl = this.thumbnailUrl
    )
}

fun PODDataTransferObject.toDomainModel(): PictureOfTheDay {
    return PictureOfTheDay(
        mediaType = this.mediaType,
        title = this.title,
        url = this.url,
        thumbnailUrl = this.thumbnailUrl
    )
}