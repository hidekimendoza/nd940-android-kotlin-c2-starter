package com.udacity.asteroidradar.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.RetrofitAsteroidsInstance
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.apipod.RetrofitPODInstance
import kotlinx.coroutines.launch
import org.json.JSONObject

private const val TAG = "MainViewModel"


class MainViewModel : ViewModel() {
    private val _asteroids: MutableLiveData<ArrayList<Asteroid>> = MutableLiveData()
    val asteroids: LiveData<ArrayList<Asteroid>>
        get() = _asteroids

    private val _error_message: MutableLiveData<String?> = MutableLiveData(null)
    val error_message: LiveData<String?>
        get() = _error_message

    private val _navigate_to_details: MutableLiveData<Asteroid?> = MutableLiveData(null)
    val navigate_to_details: LiveData<Asteroid?>
        get() = _navigate_to_details

    private val _pod: MutableLiveData<PictureOfDay?> = MutableLiveData(null)
    val pod: LiveData<PictureOfDay?>
        get() = _pod

    fun getAsteroids() {
        _error_message.value = null
        viewModelScope.launch {
            try {
                val asteroidJSON = JSONObject(RetrofitAsteroidsInstance.api.getAsteroids())
                _asteroids.value = parseAsteroidsJsonResult(asteroidJSON)
            } catch (T: Throwable) {
                _error_message.value = T.message
            }
        }
    }

    fun mainToDetailsNavigated() {
        _navigate_to_details.value = null
    }

    fun onAsteroidItemClicked(asteroid: Asteroid) {
        _navigate_to_details.value = asteroid
    }

    fun getPOD(){
        _pod.value = null
        viewModelScope.launch {
            try {
                _pod.value = RetrofitPODInstance.api.getPOD()
                Log.i(TAG, "POD JSON retrieved: data ${pod.value}")

            }
            catch (T: Throwable){
                Log.e(TAG, "Unable to get POD, reason: ${T.message}")
            }
        }
    }
}