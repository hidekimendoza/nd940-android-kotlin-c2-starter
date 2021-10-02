package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PictureOfTheDayDao {
    @Query("SELECT * FROM picture_of_the_day_table WHERE id = 0")
    fun getPictureOfTheDay(): LiveData<DatabasePictureOfTheDay>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPictureOfTheDay(pictureOfTheDay: DatabasePictureOfTheDay)
}