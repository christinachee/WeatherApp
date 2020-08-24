package com.waichee.weatherapp02.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.waichee.weatherapp02.data.entities.WeatherResponse
import com.waichee.weatherapp02.databinding.WeatherFragmentBinding
import com.waichee.weatherapp02.utils.DateTimeConverter
import com.waichee.weatherapp02.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment: Fragment() {
    private lateinit var binding: WeatherFragmentBinding
    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var adapter: DailyWeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WeatherFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.weather.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    Toast.makeText(activity, "SUCCESS", Toast.LENGTH_SHORT).show()
                    it.data?.let { it1 -> bindWeather(it1) }
                    adapter.submitList(it.data?.daily)
                }
                Resource.Status.LOADING -> Toast.makeText(activity, "LOADING", Toast.LENGTH_SHORT).show()
                Resource.Status.ERROR -> Toast.makeText(activity, "ERROR", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun bindWeather(weather: WeatherResponse) {
        binding.main.text = weather.current.weather[0].main
        binding.temperature.text = weather.current.temp.toInt().toString()
        binding.location.text = weather.timezone
        binding.currentTime.text = DateTimeConverter.getDateTimeString(weather.current.dt)
    }

    private fun setupRecyclerView() {
        adapter = DailyWeatherAdapter()
        binding.dailyRv.adapter = adapter
    }

}