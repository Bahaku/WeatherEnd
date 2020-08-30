package com.example.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.weather.data.RetrofitBuilder
import com.example.weather.data.modules.WeatherData
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RetrofitBuilder.getService()
            ?.getWeather("Bishkek", getString(R.string.appid), "metric")
            ?.enqueue(object : Callback<WeatherData> {
                override fun onResponse(
                    call: Call<WeatherData>,
                    response: Response<WeatherData>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        val data = response.body()
                        weather.text = data?.main?.temp.toString()
                        //weatherMax.text = data?.main?.tempMax.toString()
                        //weatherMin.text = data?.main?.tempMin.toString()
                    } else {
                        Toast.makeText(applicationContext, " Нет данных", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                    Toast.makeText(applicationContext, "Нет соединения", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }