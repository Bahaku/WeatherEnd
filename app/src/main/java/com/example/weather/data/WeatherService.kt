package com.example.weather.data

import com.example.weather.data.modules.ForecastModel
import com.example.weather.data.modules.WeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("data/2.5/weather")
    fun getWeather(
        @Query("q") city: String,
        @Query("appid") appId: String,
        @Query("units") units: String
    ): Call<WeatherData>

    @GET("data/2.5/forecast")
    fun getWeather1(
        @Query("q") city:String,
        @Query("appid") appId: String,
        @Query("units") units: String
    ): Call<ForecastModel>
}