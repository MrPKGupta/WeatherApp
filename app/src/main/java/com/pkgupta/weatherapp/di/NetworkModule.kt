package com.pkgupta.weatherapp.di

import com.pkgupta.weatherapp.api.ForecastEndPoint
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideUnsplashService(): ForecastEndPoint {
        return ForecastEndPoint.create()
    }
}