package com.udacity.asteroidradar.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udacity.asteroidradar.PictureOfTheDay

@Entity(tableName = "picture_of_the_day_table")
data class DatabasePictureOfTheDay constructor(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "media_type")
    val mediaType: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "thumbnail_url")
    val thumbnailUrl: String? = null
)

fun DatabasePictureOfTheDay.asDomainModel(): PictureOfTheDay {
    return PictureOfTheDay(
        mediaType = this.mediaType,
        title = this.title,
        url = this.url,
        thumbnailUrl = this.thumbnailUrl
    )
}