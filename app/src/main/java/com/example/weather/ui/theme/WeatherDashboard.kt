package com.example.weather.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.weather.data.ForecastItem
import com.example.weather.ui.WeatherUiState
import com.example.weather.ui.WeatherViewModel

@Composable
fun WeatherDashboard(viewModel: WeatherViewModel) {
    var city by remember { mutableStateOf("London") }
    val state by viewModel.state.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF001F3F), // Navy
                        Color(0xFF002E6E), // Deep Blue
                        Color(0xFF003F91)  // Royal Blue
                    )
                )
            )
            .padding(16.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Input field
            OutlinedTextField(
                value = city,
                onValueChange = { city = it },
                label = { Text("Enter city", color = Color.White, fontSize = 20.sp) },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.LightGray,
                    cursorColor = Color.White
                )
            )

            // Search button (now white)
            Button(
                onClick = { viewModel.load(city.trim()) },
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF0D47A1) // Dark blue text
                )
            ) {
                Text("Search")
            }

            Spacer(modifier = Modifier.height(16.dp))

            when {
                state.isLoading -> CircularProgressIndicator(color = Color.White)
                state.error != null -> Text(text = state.error!!, color = Color.Red)
                state.weather != null -> WeatherContent(state)
            }
        }
    }
}

@Composable
fun WeatherContent(state: WeatherUiState) {
    val weather = state.weather ?: return
    val forecast = state.forecast

    val iconUrl = "https://openweathermap.org/img/wn/${weather.weather[0].icon}@4x.png"

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Today's Forecast section (all white)
        Text(
            "Today's Forecast",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            weather.name,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Image(
            painter = rememberAsyncImagePainter(iconUrl),
            contentDescription = weather.weather[0].description,
            modifier = Modifier.size(120.dp)
        )
        Text(
            "${weather.main.temp}°C",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            weather.weather[0].description.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase() else it.toString()
            },
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White
        )
        Text(
            "Humidity: ${weather.main.humidity}%",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 3-Day Forecast
        Text(
            "3-Day Forecast",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(forecast) { item ->
                ForecastCard(item)
            }
        }
    }
}

@Composable
fun ForecastCard(item: ForecastItem) {
    val iconUrl = "https://openweathermap.org/img/wn/${item.weather[0].icon}@2x.png"
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1565C0) // Medium blue
        )
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(iconUrl),
                contentDescription = item.weather[0].description,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(item.dt_txt.substring(0, 10), fontWeight = FontWeight.Bold, color = Color.White)
                Text("${item.main.temp}°C", color = Color.White)
                Text(
                    item.weather[0].description.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase() else it.toString()
                    },
                    color = Color.White
                )
            }
        }
    }
}
