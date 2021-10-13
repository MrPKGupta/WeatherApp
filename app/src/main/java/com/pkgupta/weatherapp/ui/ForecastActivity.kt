package com.pkgupta.weatherapp.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.work.*
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import com.pkgupta.weatherapp.ForecastWorker
import com.pkgupta.weatherapp.R
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class ForecastActivity : AppCompatActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    private val forecastViewModel: ForecastViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        setForeCastRefreshInterval()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        requestPermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    getLocation()
                } else {
                    Snackbar
                        .make(findViewById(android.R.id.content), R.string.gps_permission, Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.allow, View.OnClickListener {
                            checkPermission()
                        })
                        .show()
                }
            }

        checkPermission()

    }

    private fun checkPermission() {
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                {
                getLocation()
            } else {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
            }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->

                if(location != null) {
                    forecastViewModel.fetchForecast(location.latitude, location.longitude)
                }
            }
    }

    private fun setForeCastRefreshInterval() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .build()

        val forecastWorkRequest: WorkRequest = PeriodicWorkRequestBuilder<ForecastWorker>(2, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()

        WorkManager
            .getInstance(this)
            .enqueue(forecastWorkRequest)
    }
}