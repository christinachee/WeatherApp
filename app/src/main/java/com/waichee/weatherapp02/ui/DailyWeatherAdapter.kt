package com.waichee.weatherapp02.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.waichee.weatherapp02.data.entities.Daily
import com.waichee.weatherapp02.databinding.ItemDailyWeatherBinding
import javax.inject.Inject

class DailyWeatherAdapter @Inject constructor() : ListAdapter<Daily, DailyWeatherAdapter.DailyWeatherVH>(DiffCallback) {

    class DailyWeatherVH(
        private var binding: ItemDailyWeatherBinding
    ): RecyclerView.ViewHolder(binding.root) {

        private lateinit var daily: Daily

        fun bind(daily:Daily) {
            this.daily = daily
            binding.daily = daily
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyWeatherVH {
        val binding = ItemDailyWeatherBinding.inflate(LayoutInflater.from(parent.context))
        return DailyWeatherVH(binding)
    }

    override fun onBindViewHolder(holder: DailyWeatherVH, position: Int) {
        val daily = getItem(position)
        if (daily != null) {
            holder.bind(daily)
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Daily>() {
        override fun areItemsTheSame(oldItem: Daily, newItem: Daily): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Daily, newItem: Daily): Boolean {
            return oldItem.dt == newItem.dt
        }
    }
}