package com.udacity.asteroidradar.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.api.AsteroidContainer
import com.udacity.asteroidradar.api.RetrofitAsteroidsInstance
import com.udacity.asteroidradar.api.asDatabaseModel
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.database.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class AsteroidRepository(val asteroidDatabase: AsteroidDatabase) {

    val asteroids: LiveData<List<Asteroid>> = Transformations.map(asteroidDatabase.asteroidDao.getAsteroids()){
        it.asDomainModel()
    }

    suspend fun updateAsteroids(){
        withContext(Dispatchers.IO){
            Log.i("Hideki", "Start")
            val asteroidsString = RetrofitAsteroidsInstance.api.getAsteroids()
            Log.i("Hideki", asteroidsString)
            val asteroids = AsteroidContainer(parseAsteroidsJsonResult(JSONObject(asteroidsString)))
            asteroidDatabase.asteroidDao.insertAllAsteroids(*asteroids.asDatabaseModel())
        }
    }
}