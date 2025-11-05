package com.example.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import com.example.weather.ui.theme.WeatherDashboard
import com.example.weather.ui.WeatherViewModel
import androidx.compose.material3.MaterialTheme
import com.example.weather.repository.WeatherRepository

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = WeatherRepository()
        val viewModel = WeatherViewModel(repository)

        setContent {
            MaterialTheme {
                WeatherDashboard(viewModel)
            }
        }
    }
}
