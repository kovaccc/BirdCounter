package com.example.birdcounter.data


import android.content.SharedPreferences
import android.graphics.Color
import com.example.birdcounter.utils.*
import com.example.birdcounter.di.SharedPrefModule
import javax.inject.Inject
import javax.inject.Singleton


enum class Counter(val colorValue: Int, val key: String){
    BLUE(Color.BLUE, KEY_BLUE_COUNTER),
    RED(Color.RED, KEY_RED_COUNTER),
    GREEN(Color.GREEN, KEY_GREEN_COUNTER),
    GRAY(Color.GRAY, KEY_GRAY_COUNTER),
    WHITE(Color.WHITE, KEY_WHITE_COUNTER); // default counter

    companion object {
        @JvmStatic
        fun fromString(key: String): Counter =
            values().find { value -> value.key == key } ?: WHITE
    }
}


@Singleton
class PrefsManager @Inject constructor( @SharedPrefModule.CounterSharedPreferences val counterSharedPrefs: SharedPreferences) {

    fun saveCounterValue(value: Int, key: String) {
       counterSharedPrefs.edit().putInt(key, value).apply()
    }

    fun getCounterValue(key: String) : Int {
        return counterSharedPrefs.getInt(key, 0)
    }

    fun getLastCounter() : String? {
        return counterSharedPrefs.getString(KEY_LAST_COUNTER, KEY_WHITE_COUNTER)
    }

    fun saveLastCounter(counterColor: String) {
        counterSharedPrefs.edit().putString(KEY_LAST_COUNTER, counterColor).apply()
    }

}