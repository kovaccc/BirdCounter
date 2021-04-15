package com.example.birdcounter.viewmodels

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.birdcounter.utils.KEY_BLUE_COUNTER
import com.example.birdcounter.utils.KEY_GRAY_COUNTER
import com.example.birdcounter.utils.KEY_GREEN_COUNTER
import com.example.birdcounter.utils.KEY_RED_COUNTER
import com.example.birdcounter.data.Counter
import com.example.birdcounter.data.PrefsManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


private const val TAG = "MainViewModel"

@HiltViewModel
class MainViewModel @Inject constructor(private val prefsManager: PrefsManager) : ViewModel() {

    private val _currentCounterMLD = MutableLiveData<Int>()
    val currentCounterLD: LiveData<Int>
        get() = _currentCounterMLD


    private val _currentColorMLD = MutableLiveData<Counter>()
    val currentColorLD: LiveData<Counter>
        get() = _currentColorMLD


    private val counterListener = SharedPreferences.OnSharedPreferenceChangeListener{ sharedPreferences, key ->
        Log.d(TAG, "counterListener: started")
        when(key) {
            KEY_BLUE_COUNTER -> {

                _currentCounterMLD.value = prefsManager.getCounterValue(key)
                prefsManager.saveLastCounter(key)
                _currentColorMLD.value = Counter.BLUE

                Log.d(TAG, "counterListener: new color is ${_currentColorMLD.value} and counter value ${_currentCounterMLD.value}")

            }

            KEY_GRAY_COUNTER -> {

                _currentCounterMLD.value = prefsManager.getCounterValue(key)
                prefsManager.saveLastCounter(key)
                _currentColorMLD.value = Counter.GRAY

                Log.d(TAG, "counterListener: new color is ${_currentColorMLD.value} and counter value ${_currentCounterMLD.value}")


            }


            KEY_GREEN_COUNTER -> {

                _currentCounterMLD.value = prefsManager.getCounterValue(key)
                prefsManager.saveLastCounter(key)
                _currentColorMLD.value = Counter.GREEN

                Log.d(TAG, "counterListener: new color is ${_currentColorMLD.value} and counter value ${_currentCounterMLD.value}")


            }

            KEY_RED_COUNTER -> {

                _currentCounterMLD.value = prefsManager.getCounterValue(key)
                prefsManager.saveLastCounter(key)
                _currentColorMLD.value = Counter.RED

                Log.d(TAG, "counterListener: new color is ${_currentColorMLD.value} and counter value ${_currentCounterMLD.value}")

            }

        }

        Log.d(TAG, "counterListener: ends")
    }

    init {
        prefsManager.counterSharedPrefs.registerOnSharedPreferenceChangeListener(counterListener)
        val lastCounter = prefsManager.getLastCounter()!!// string key

        _currentCounterMLD.value = prefsManager.getCounterValue(lastCounter)

        _currentColorMLD.value = Counter.fromString(lastCounter)

    }


    fun incrementCounter(key: String) {
        val lastValue = prefsManager.getCounterValue(key)
        prefsManager.saveCounterValue(lastValue + 1, key)
    }


    fun resetCounter(key: String) {
        prefsManager.saveCounterValue(0, key)
    }



    override fun onCleared() {
        Log.d(TAG, "onCleared: starts")
        super.onCleared()
        prefsManager.counterSharedPrefs.unregisterOnSharedPreferenceChangeListener(counterListener)
        Log.d(TAG, "onCleared: ends")
    }

}