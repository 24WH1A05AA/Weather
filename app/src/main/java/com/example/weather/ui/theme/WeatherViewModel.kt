package com.example.weather.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.ForecastItem

import com.example.weather.data.WeatherResponse
import com.example.weather.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class WeatherUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val weather: WeatherResponse? = null,
    val forecast: List<ForecastItem> = emptyList()
)

class WeatherViewModel(private val repo: WeatherRepository) : ViewModel() {

    private val _state = MutableStateFlow(WeatherUiState())
    val state: StateFlow<WeatherUiState> = _state

    fun load(city: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                val weather: WeatherResponse = repo.getCurrentWeather(city)
                val forecastResponse = repo.getForecast(city)
                // pick first item for each date, take next 3 dates
                val grouped = forecastResponse.list.groupBy { it.dt_txt.substring(0, 10) }
                val forecast: List<ForecastItem> = grouped.entries
                    .sortedBy { it.key } // ensure order
                    .take(3)
                    .map { it.value.first() }

                _state.value = WeatherUiState(
                    isLoading = false,
                    weather = weather,
                    forecast = forecast
                )
            } catch (e: Exception) {
                _state.value = WeatherUiState(
                    isLoading = false,
                    error = "City not found or network error"
                )
            }
        }
    }
}
