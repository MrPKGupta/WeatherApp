package com.pkgupta.weatherapp.api

import android.app.Activity
import com.pkgupta.weatherapp.persist.PrefManager
import com.pkgupta.weatherapp.ui.Forecast
import javax.inject.Inject

class ForecastRepository @Inject constructor(
    private val forecastService: ForecastService,
    private val prefManager: PrefManager
) {
    suspend fun getForecast(lat: Double, long: Double, activity: Activity) : Forecast? {
        return getForecastData(lat, long, activity)
    }

    private suspend fun getForecastData(lat: Double, long: Double, activity: Activity): Forecast? {
        if(prefManager.readForecast(activity) == null) {
            val forecastResponse = forecastService.getForecast(lat, long,
                "1dd37794190761971d3b98a6b6319fc5", "metric")

            val forecast = Forecast(forecastResponse.name, forecastResponse.weather[0].main,
                forecastResponse
                .main.temp,
                forecastResponse.main.tempMin, forecastResponse.main.tempMax)

            prefManager.persistForecast(forecast, activity)

            return forecast
        }

        return null
    }
}