package com.pkgupta.weatherapp.persist

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.GsonBuilder
import com.pkgupta.weatherapp.ui.Forecast

class PrefManager {

    companion object {
        const val KEY_FORECAST = "forecast"
        const val PREF_STRING = "com.pkgupta.weather_prefs"

        fun persistForecast(forecast: Forecast, context: Context?) {
            val prefs = context?.getSharedPreferences(PREF_STRING, Context.MODE_PRIVATE) ?: return
            val forecastString = GsonBuilder().create().toJson(forecast)
            with(prefs.edit()) {
                putString(KEY_FORECAST, forecastString)
                apply()
            }
        }

        fun readForecast(context: Context?) : Forecast? {
            val prefs = context?.getSharedPreferences(PREF_STRING, Context.MODE_PRIVATE) ?: return null
            val forecastString = prefs.getString(KEY_FORECAST, null)
            return GsonBuilder().create().fromJson(forecastString, Forecast::class.java)
        }

        fun clearForecast(context: Context?) {
            val prefs = context?.getSharedPreferences(PREF_STRING, Context.MODE_PRIVATE) ?: return
            prefs.edit().clear().apply()
        }
    }
}