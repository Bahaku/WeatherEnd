package com.example.weather.data.modules

data class ForecastModel (
    val cod: String,
    val message: Int,
    val cnt: Int,
    val city: CityModel,
    val list: List<ForecastItem>
)


data class CityModel(
    val id: Int,
    val name: String,
    val country: String,
    val population: Int,
    val timezone: Int,
    val sunrise: Int,
    val sunset: Int,
    val coord: Coord1
)


data class Coord1 (
    val lat: Double,
    val lon: Double
)

