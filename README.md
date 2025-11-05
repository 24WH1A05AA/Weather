# 🌤️ Weather App (Kotlin + Jetpack Compose)

A modern weather forecast Android app built with **Kotlin**, **Jetpack Compose**, and **OpenWeatherMap API**.  
It displays the **current weather** and a **3-day forecast**, with a clean dark blue gradient theme.

---

## ✨ Features

- 🌡️ Real-time current weather by city name  
- 📅 3-day forecast view with temperature, weather icons, and conditions  
- 🎨 Jetpack Compose UI with dark blue gradient theme  
- ⚡ Powered by **Retrofit**, **Coroutines**, and **ViewModel (MVVM)**  
- 🖼️ Dynamic weather icons via **Coil** image loader  
- 🗄️ Ready for offline caching with Room (future enhancement)

---

## 🧰 Tech Stack

| Component | Library |
|------------|----------|
| UI | Jetpack Compose (Material 3) |
| Architecture | MVVM + ViewModel + Repository |
| Networking | Retrofit2 + Gson |
| Asynchronous | Kotlin Coroutines |
| Image Loading | Coil |
| Local Storage | Room (optional) |
| API Provider | OpenWeatherMap |

---

## 🔑 Setup Instructions

### 1. Get your API key from [OpenWeatherMap](https://openweathermap.org/api)

Sign up and generate an **API key**.

### 2. Add your API key in `local.properties`
At the bottom of your `local.properties` file, add this line:

OPEN_WEATHER_API_KEY=your_api_key_here

bash
Copy code

### 3. Build the app
./gradlew clean build
4. Run the app
Select your device/emulator and hit Run ▶️ in Android Studio.

🧭 Project Structure
css
Copy code
Weather/
 ├── app/
 │   ├── src/
 │   │   ├── main/
 │   │   │   ├── java/com/example/weather/
 │   │   │   │   ├── MainActivity.kt
 │   │   │   │   ├── network/
 │   │   │   │   ├── repository/
 │   │   │   │   ├── ui/
 │   │   │   │   └── model/
 │   │   │   └── res/
 │   │   └── AndroidManifest.xml
 │   └── build.gradle.kts
 ├── local.properties
 └── README.md
🖼️ Screenshots (Optional)
Current Weather	3-Day Forecast
🌇
🌤️

🤝 Contributing
Pull requests are welcome!
If you'd like to add new features like hourly forecasts, weather alerts, or location-based detection, feel free to open an issue first.

🪪 License
This project is licensed under the MIT License — see the LICENSE file for details.

💙 Made with Jetpack Compose and OpenWeatherMap API
yaml



