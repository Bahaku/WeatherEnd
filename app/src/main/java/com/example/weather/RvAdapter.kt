package com.example.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.data.modules.ForecastItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rv_weather.view.*

class RvAdapter: RecyclerView.Adapter<RvHolder>(){

    private val list = arrayListOf<ForecastItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_weather, parent, false)
        return RvHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RvHolder, position: Int) {
        holder.bind(list[position])
    }

    fun update(list: List<ForecastItem>?) {
        if (list != null){
            this.list.clear()
            this.list.addAll(list)
            notifyDataSetChanged()
        }
    }

}



class RvHolder(v: View) : RecyclerView.ViewHolder(v) {

    fun bind(forecastItem: ForecastItem) {
        itemView.tvText.text = forecastItem.main.temp.toInt().toString()
        val image = forecastItem.weather.first().icon
        Picasso.get().load(itemView.context.getString(R.string.image_url, image))
            .into(itemView.weatherImage)
    }

}