package com.example.weather.data

// Current weather response
data class WeatherResponse(
    val name: String,
    val weather: List<WeatherDesc>,
    val main: MainInfo
)

data class WeatherDesc(
    val main: String,
    val description: String,
    val icon: String
)

data class MainInfo(
    val temp: Double,
    val humidity: Int
)

// Forecast response (3-hour interval items)
data class ForecastResponse(
    val list: List<ForecastItem>
)

data class ForecastItem(
    val dt_txt: String,
    val main: MainInfo,
    val weather: List<WeatherDesc>
)
