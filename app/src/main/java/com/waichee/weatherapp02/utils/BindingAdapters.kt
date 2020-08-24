package com.waichee.weatherapp02.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import timber.log.Timber

@BindingAdapter("weatherIcon")
fun bindWeatherIcon(imageView: ImageView, icon: String?) {
    icon?.let {
        Picasso.get().load("http://openweathermap.org/img/wn/$icon@2x.png").into(imageView)
    }
}

@BindingAdapter("temp")
fun bindTemp(textView: TextView, temperature: Double?) {
    temperature?.let {
        textView.text = temperature.toInt().toString()
    }
}

@BindingAdapter("weekday")
fun bindWeekday(textView: TextView, dt: Int?) {
    dt?.let {
        textView.text = DateTimeConverter.getWeekdayString(dt)
    }
}