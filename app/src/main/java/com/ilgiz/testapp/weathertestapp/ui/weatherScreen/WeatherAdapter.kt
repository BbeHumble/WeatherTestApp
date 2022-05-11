package com.ilgiz.testapp.weathertestapp.ui.weatherScreen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ilgiz.testapp.weathertestapp.R
import com.ilgiz.testapp.weathertestapp.data.model.WeatherItem
import com.ilgiz.testapp.weathertestapp.utils.getDateOfDay
import com.ilgiz.testapp.weathertestapp.utils.getDayOfWeek

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {
    private var weatherList: ArrayList<WeatherItem> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.li_weather, parent, false)

        return ViewHolder(view)
    }

    fun setData(
        data: List<WeatherItem>
    ) {
        weatherList = data as ArrayList<WeatherItem>
        notifyDataSetChanged()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = weatherList[position]
        holder.dayOfWeek.text = item.date.getDayOfWeek()
        holder.dateOfDay.text = item.date.getDateOfDay()
        holder.temperature.text = "${item.temperature} ℃"

    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val dayOfWeek: TextView = itemView.findViewById(R.id.dayOfWeek)
        val dateOfDay: TextView = itemView.findViewById(R.id.dateOfDay)
        val temperature: TextView = itemView.findViewById(R.id.temperature)
    }
}