package com.udacity.asteroidradar.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.PictureOfTheDay
import com.udacity.asteroidradar.database.getDatabase
import com.udacity.asteroidradar.repository.AsteroidRepository
import kotlinx.coroutines.launch

private const val TAG = "MainViewModel"

enum class AsteroidFilter {
    TODAY,
    WEEK,
    SAVED
}

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _asteroidFilter = MutableLiveData<AsteroidFilter>(AsteroidFilter.WEEK)

    private val database = getDatabase(application)

    private val asteroidRepository = AsteroidRepository(database)

    val asteroids: LiveData<List<Asteroid>> = Transformations.switchMap(_asteroidFilter) { filter ->
        filter?.let {
            when (filter) {
                AsteroidFilter.WEEK -> asteroidRepository.CurrentWeekAsteroids
                AsteroidFilter.TODAY -> asteroidRepository.TodaysAsteroids
                AsteroidFilter.SAVED -> asteroidRepository.allAsteroids
                else -> asteroidRepository.allAsteroids
            }
        }
    }
    val pod: LiveData<PictureOfTheDay> = asteroidRepository.pictureOfTheDay

    private val _error_message: MutableLiveData<String?> = MutableLiveData(null)
    val error_message: LiveData<String?>
        get() = _error_message

    private val _navigate_to_details: MutableLiveData<Asteroid?> = MutableLiveData(null)
    val navigate_to_details: LiveData<Asteroid?>
        get() = _navigate_to_details

    private val _is_fetching_asteroids: MutableLiveData<Boolean> = MutableLiveData(false)
    val is_fetching_asteroids: LiveData<Boolean> = _is_fetching_asteroids

    fun getAsteroids() {
        _is_fetching_asteroids.value = true
        _error_message.value = null
        viewModelScope.launch {
            try {
                asteroidRepository.updateAsteroids()
            } catch (T: Throwable) {
                _error_message.value = T.message
            }
        }
        _is_fetching_asteroids.value = false
    }

    fun mainToDetailsNavigated() {
        _navigate_to_details.value = null
    }

    fun onAsteroidItemClicked(asteroid: Asteroid) {
        _navigate_to_details.value = asteroid
    }

    fun getPOD() {
        viewModelScope.launch {
            try {
                asteroidRepository.updatePictureOfTheDay()
                Log.i(TAG, "POD JSON retrieved: data ${pod.value}")
            } catch (T: Throwable) {
                Log.e(TAG, "Unable to get POD, reason: ${T.message}")
            }
        }
    }

    fun removePastAsteroids() {
        viewModelScope.launch {
            asteroidRepository.deletePastAsteroids()
            Log.i(TAG, "Asteroids previous of Today has been removed from database")
        }
    }

    fun getTodaysAsteroids() {
        Log.i(TAG, "Displaying todays asteroids")
        _asteroidFilter.value = AsteroidFilter.TODAY
    }

    fun getAllAsteroids() {
        Log.i(TAG, "Displaying all asteroids")
        _asteroidFilter.value = AsteroidFilter.SAVED
    }

    fun getWeekAsteroids() {
        Log.i(TAG, "Displaying week asteroids")
        _asteroidFilter.value = AsteroidFilter.WEEK
    }

    init {
        getAsteroids()
        getPOD()
    }
}