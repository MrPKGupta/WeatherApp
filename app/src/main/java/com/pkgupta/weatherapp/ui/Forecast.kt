package com.pkgupta.weatherapp.ui

data class Forecast(
    val placeName: String = "--",
    val weatherDesc: String = "--",
    val currentTemp: Double = 0.0,
    val minTemp: Double = 0.0,
    val maxTemp: Double = 0.0,
)