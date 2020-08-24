package com.waichee.weatherapp02.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.model.GradientColor
import com.waichee.weatherapp02.R
import com.waichee.weatherapp02.data.entities.WeatherResponse
import com.waichee.weatherapp02.databinding.WeatherFragmentBinding
import com.waichee.weatherapp02.utils.DateTimeConverter
import com.waichee.weatherapp02.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList


@AndroidEntryPoint
class WeatherFragment: Fragment() {
    private lateinit var binding: WeatherFragmentBinding
    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var adapter: DailyWeatherAdapter
    private val values = ArrayList<Entry>()

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
        setupChart()
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

        // Chart
        weather.hourly.map {
            values.add(Entry(it.dt.toFloat(), it.temp.toFloat()))
        }
        val hourlyDataSet = LineDataSet(values.take(5), "DataSet1")
        val hourlyData = LineData(hourlyDataSet)

        hourlyDataSet.setGradientColor(Color.CYAN, Color.RED)
        hourlyDataSet.setCircleColor(Color.WHITE)
        hourlyDataSet.lineWidth = 3.0F

        hourlyData.setValueTextColor(Color.WHITE)
        hourlyData.setValueTextSize(9f)

        binding.hourlyChart.data = hourlyData
        binding.hourlyChart.invalidate()
    }

    private fun setupChart() {
        val chart = binding.hourlyChart

        chart.setBackgroundColor(Color.TRANSPARENT)
        chart.description.isEnabled = false
        chart.setTouchEnabled(false)

        val xAxis: XAxis = chart.xAxis
        val leftAxis: YAxis = chart.axisLeft
        val rightAxis: YAxis = chart.axisRight
        val legend: Legend = chart.legend

        xAxis.setDrawAxisLine(false)
        xAxis.setDrawGridLines(false)
        xAxis.textColor = Color.WHITE
        xAxis.valueFormatter = object : ValueFormatter() {
            private val mFormat: SimpleDateFormat = SimpleDateFormat("K aa", Locale.ENGLISH)
            override fun getFormattedValue(value: Float): String {
                val millis: Long = TimeUnit.HOURS.toMillis(value.toLong())
                return mFormat.format(Date(millis))
            }
        }

        leftAxis.setDrawAxisLine(false)
        leftAxis.isEnabled = false

        rightAxis.setDrawAxisLine(false)
        rightAxis.isEnabled = false

        legend.isEnabled = false
    }

    private fun setupRecyclerView() {
        adapter = DailyWeatherAdapter()
        binding.dailyRv.adapter = adapter
    }

}