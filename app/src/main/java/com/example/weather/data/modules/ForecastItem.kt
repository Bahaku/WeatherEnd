package com.example.weather.data.modules

import com.google.gson.annotations.SerializedName

data class ForecastItem(
    val dt: Int,
    val visibility: Int,
    val pop: Double,
    val dt_txt: String,
    val main: ForecastMain,
    @SerializedName("weather") val weather: List<Weather>,


    )

data class ForecastMain(
    val temp: Double
)