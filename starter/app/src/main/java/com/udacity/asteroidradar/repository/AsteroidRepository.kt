package com.udacity.asteroidradar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.PictureOfTheDay
import com.udacity.asteroidradar.api.*
import com.udacity.asteroidradar.apipod.RetrofitPODInstance
import com.udacity.asteroidradar.apipod.toDatabaseModel
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.database.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject


class AsteroidRepository(val asteroidDatabase: AsteroidDatabase) {

    val allAsteroids: LiveData<List<Asteroid>> = Transformations.map(asteroidDatabase.asteroidDao.getAllAsteroids()){
        it.asDomainModel()
    }

    val TodaysAsteroids: LiveData<List<Asteroid>> = Transformations.map(asteroidDatabase.asteroidDao.getDayAsteroids(getNextSevenDaysFormattedDates()[0])){
        it.asDomainModel()
    }

    val CurrentWeekAsteroids: LiveData<List<Asteroid>> = Transformations.map(asteroidDatabase.asteroidDao.getWeekAsteroids(
        getNextSevenDaysFormattedDates()[0], getNextSevenDaysFormattedDates()[Constants.DEFAULT_END_DATE_DAYS - 1])){
        it.asDomainModel()
    }

    val pictureOfTheDay: LiveData<PictureOfTheDay> = Transformations.map(asteroidDatabase.pictureOfTheDayDao.getPictureOfTheDay()){
        it.asDomainModel()
    }

    suspend fun updateAsteroids(){
        withContext(Dispatchers.IO){
            val asteroidsString = RetrofitAsteroidsInstance.api.getAsteroids()
            val asteroids = AsteroidContainer(parseAsteroidsJsonResult(JSONObject(asteroidsString)))
            asteroidDatabase.asteroidDao.insertAllAsteroids(*asteroids.asDatabaseModel())
        }
    }

    suspend fun updatePictureOfTheDay(){
        withContext(Dispatchers.IO){
            val pod = RetrofitPODInstance.api.getPOD()
            asteroidDatabase.pictureOfTheDayDao.insertPictureOfTheDay(pod.toDatabaseModel())
        }
    }

    suspend fun deletePastAsteroids(){
        withContext(Dispatchers.IO){
            asteroidDatabase.asteroidDao.deletePastToDateAsteroids(getNextSevenDaysFormattedDates()[0])
        }
    }
}