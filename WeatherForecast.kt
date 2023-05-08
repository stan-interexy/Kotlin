// WeatherForecast.kt

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

// Data class to represent temperature
data class Temperature(val value: Double, val unit: String)

// Sealed class for weather conditions
sealed class WeatherCondition {
    object Sunny : WeatherCondition()
    object Cloudy : WeatherCondition()
    object Rainy : WeatherCondition()
    object Snowy : WeatherCondition()
}

// Data class to represent weather forecast
data class WeatherForecast(
    val condition: WeatherCondition,
    val temperature: Temperature,
    val city: String
)

// Extension function to format WeatherForecast output
fun WeatherForecast.format(): String {
    val conditionString = when (condition) {
        is WeatherCondition.Sunny -> "Sunny"
        is WeatherCondition.Cloudy -> "Cloudy"
        is WeatherCondition.Rainy -> "Rainy"
        is WeatherCondition.Snowy -> "Snowy"
    }
    return "Weather in $city: $conditionString, ${temperature.value} ${temperature.unit}"
}

// Mock API to fetch weather forecast
suspend fun fetchWeatherForecast(city: String): WeatherForecast {
    delay(1000) // Simulate network delay

    // Mock data
    return WeatherForecast(
        condition = WeatherCondition.Sunny,
        temperature = Temperature(25.0, "°C"),
        city = city
    )
}

fun main() = runBlocking {
    val city = "San Francisco"
    val forecast = fetchWeatherForecast(city)
    println(forecast.format())
}