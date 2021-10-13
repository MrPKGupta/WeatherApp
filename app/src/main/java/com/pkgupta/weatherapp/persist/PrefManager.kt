package com.pkgupta.weatherapp

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import com.pkgupta.weatherapp.ui.Forecast

class PrefManager {

    fun persistForecast(forecast: Forecast, activity: Activity?) {
        val prefs = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        val forecastString = GsonBuilder().create().toJson(forecast)
        with(prefs.edit()) {
            putString(KEY_FORECAST, forecastString)
            apply()
        }
    }

    fun readForecast(activity: Activity?) : Forecast? {
        val prefs = activity?.getPreferences(Context.MODE_PRIVATE) ?: return null
        val forecastString = prefs.getString(KEY_FORECAST, null)
        return GsonBuilder().create().fromJson(forecastString, Forecast::class.java)
    }

    companion object {
        const val KEY_FORECAST = "forecast"
    }
}