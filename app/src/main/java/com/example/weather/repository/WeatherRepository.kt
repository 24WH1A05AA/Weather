package com.example.weather.repository

import com.example.weather.BuildConfig
import com.example.weather.data.ForecastResponse
import com.example.weather.data.WeatherResponse
import com.example.weather.network.WeatherApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherRepository {

    private val api: WeatherApi

    init {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        api = retrofit.create(WeatherApi::class.java)
    }

    suspend fun getCurrentWeather(city: String): WeatherResponse =
        api.getCurrentWeather(city, BuildConfig.OPEN_WEATHER_API_KEY)

    suspend fun getForecast(city: String): ForecastResponse =
        api.getForecast(city, BuildConfig.OPEN_WEATHER_API_KEY)
}
