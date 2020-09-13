package com.example.weather

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.weather.data.RetrofitBuilder
import com.example.weather.data.modules.ForecastModel
import com.example.weather.data.modules.WeatherData
import kotlinx.android.synthetic.main.activity_main_copy.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_copy)
        formDate()

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
                        weather.text = getString(R.string.currentFormat, data?.main?.temp?.toInt().toString())
                        windNumber.text = getString(R.string.windFormat, data?.wind?.speed?.toInt().toString())
                        pressureNumber.text = getString(R.string.pressureFormat, data?.main?.pressure.toString())
                        humidityNumber.text = getString(R.string.humidityFormat, data?.main?.humidity)
                        sunriseTime.text = formatDate(data?.sys?.sunrise)
                        sunsetTime.text = formatDate(data?.sys?.sunset)
                        cloudinessNumber.text = getString(R.string.humidityFormat, data?.clouds?.all)

                    } else {
                        Toast.makeText(applicationContext, " Нет данных", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                    Toast.makeText(applicationContext, "Нет соединения", Toast.LENGTH_SHORT).show()
                }
            })

        fun views(response: Response<WeatherData>){

        }

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

    private fun formDate(){
        val sfd = SimpleDateFormat("d", Locale.getDefault())
        val dateCurrent = Date()
        textDay.text = sfd.format(dateCurrent)
        val sfdMonth = SimpleDateFormat("MMMM\nyyyy", Locale.getDefault())
        val month = sfdMonth.format(dateCurrent)
        tvMonth.text = month
    }

    fun formatDate(date: Int?): String {
        val newdata = date?.toLong()?:0
        return SimpleDateFormat("H:mm", Locale.getDefault()).format(Date(newdata * 1000))
    }


}
