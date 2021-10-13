package com.pkgupta.weatherapp.ui

import android.app.Application
import androidx.lifecycle.*
import androidx.work.*
import com.pkgupta.weatherapp.ForecastWorker
import com.pkgupta.weatherapp.data.ForecastRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val forecastRepository: ForecastRepository,
    val app: Application
) : AndroidViewModel(app) {
    private val _forecast = MutableLiveData<Forecast>()
    val forecast: LiveData<Forecast> = _forecast
    private val workManager: WorkManager = WorkManager.getInstance(app)

    init {
        _forecast.value = Forecast()
        setForeCastRefreshInterval()
    }

    fun fetchForecast(lat: Double, long: Double) {
        viewModelScope.launch {
            _forecast.value = forecastRepository.getForecast(lat, long, app)
        }
    }

    private fun setForeCastRefreshInterval() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .build()

        val forecastWorkRequest: PeriodicWorkRequest = PeriodicWorkRequestBuilder<ForecastWorker>(2, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()

        workManager.enqueueUniquePeriodicWork("abc", ExistingPeriodicWorkPolicy.KEEP,
            forecastWorkRequest)
    }
}
