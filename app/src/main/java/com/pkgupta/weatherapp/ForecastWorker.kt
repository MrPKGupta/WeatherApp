package com.pkgupta.weatherapp

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.pkgupta.weatherapp.data.ForecastRepository
import com.pkgupta.weatherapp.persist.PrefManager

class ForecastWorker(
    val appContext: Context,
    workerParams: WorkerParameters,
    val forecastRepository: ForecastRepository
): Worker(appContext, workerParams) {

    override fun doWork(): Result {
        forecastRepository.fetchForecast(1.0, 1.0, appContext)
        return Result.success()
    }
}