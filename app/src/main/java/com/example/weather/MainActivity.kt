package com.example.weather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.weather.data.RetrofitBuilder
import com.example.weather.data.modules.ForecastModel
import com.example.weather.data.modules.WeatherData
import kotlinx.android.synthetic.main.activity_main_copy.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_copy)

        val adapter = RvAdapter()
        rvCycle.adapter = adapter

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

                    } else {
                        Toast.makeText(applicationContext, " Нет данных", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                    Toast.makeText(applicationContext, "Нет соединения", Toast.LENGTH_SHORT).show()
                }
            })

        RetrofitBuilder.getService()
            ?.getWeather1("Bishkek", getString(R.string.appid), "metric")
            ?.enqueue(object : Callback<ForecastModel> {
                override fun onResponse(
                    call: Call<ForecastModel>,
                    response: Response<ForecastModel>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        adapter.update(response.body()?.list)
                    } else {
                        Toast.makeText(applicationContext, " Нет данных", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ForecastModel>, t: Throwable) {
                    Toast.makeText(applicationContext, "Нет соединения", Toast.LENGTH_SHORT).show()
                }
            })

        }


}
