package com.example.weather.data.modules

data class ForecastItem(
    val dt: Int,
    val visibility: Int,
    val pop: Double,
    val dt_txt: String,
    val main: ForecastMain

)

data class ForecastMain(
    val temp: Double
)