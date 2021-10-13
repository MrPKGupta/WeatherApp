package com.pkgupta.weatherapp

import com.pkgupta.weatherapp.ui.Forecast

class ForecastOperationImpl: ForecastOperation {
    override val refreshPeriodInHr: Int = 2

    override fun setRefreshPeriod(hours: Int) {
        TODO("Not yet implemented")
    }

    override fun fetchForecast(lat: Double, long: Double): Forecast? {
        TODO("Not yet implemented")
    }
}