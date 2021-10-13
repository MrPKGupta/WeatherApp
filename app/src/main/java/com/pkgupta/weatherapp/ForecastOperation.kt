package com.pkgupta.weatherapp

import com.pkgupta.weatherapp.ui.Forecast

interface ForecastOperation {
    val refreshPeriodInHr: Int

    fun setRefreshPeriod(hours: Int)
    fun fetchForecast(lat: Double, long: Double): Forecast?
}