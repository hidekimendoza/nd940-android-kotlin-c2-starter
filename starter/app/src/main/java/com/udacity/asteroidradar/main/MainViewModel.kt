package com.udacity.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.api.RetrofitInstance
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import kotlinx.coroutines.launch
import org.json.JSONObject


class MainViewModel : ViewModel() {
    private val _asteroids: MutableLiveData<ArrayList<Asteroid>> = MutableLiveData()
    val asteroids: LiveData<ArrayList<Asteroid>>
        get() = _asteroids

    private val _error_message: MutableLiveData<String?> = MutableLiveData(null)
    val error_message: LiveData<String?>
        get() = _error_message

    fun getAsteroids() {
        _error_message.value = null
        viewModelScope.launch {
            try {
                val asteroidJSON = JSONObject(RetrofitInstance.api.getAsteroids())
                _asteroids.value = parseAsteroidsJsonResult(asteroidJSON)
            } catch (T: Throwable) {
                _error_message.value = T.message
            }
        }
    }
}