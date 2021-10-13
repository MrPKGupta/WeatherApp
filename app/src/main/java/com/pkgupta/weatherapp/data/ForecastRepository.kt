package com.pkgupta.weatherapp.data

import android.content.Context
import com.pkgupta.weatherapp.api.ForecastEndPoint
import com.pkgupta.weatherapp.persist.PrefManager
import com.pkgupta.weatherapp.ui.Forecast
import javax.inject.Inject

class ForecastRepository @Inject constructor(
    private val forecastEndPoint: ForecastEndPoint,
) {

    suspend fun getForecast(lat: Double, long: Double, context: Context): Forecast? {
        return if (PrefManager.readForecast(context) == null) {
            fetchForecast(lat, long, context)
        } else {
            PrefManager.readForecast(context)
        }
    }

    suspend fun fetchForecast(lat: Double, long: Double, context: Context): Forecast {
        val forecastResponse = forecastEndPoint.getForecast(
            lat, long,
            "1dd37794190761971d3b98a6b6319fc5", "metric"
        )

        val forecast = Forecast(
            forecastResponse.name, forecastResponse.weather[0].main,
            forecastResponse.main.temp, forecastResponse.main.tempMin, forecastResponse.main.tempMax
        )

        PrefManager.persistForecast(forecast, context)

        return forecast
    }
}