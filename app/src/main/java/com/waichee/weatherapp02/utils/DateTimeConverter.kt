package com.waichee.weatherapp02.utils

import java.text.SimpleDateFormat
import java.util.*

object DateTimeConverter {
    private val dateTimeFormat = SimpleDateFormat("dd MMMM, K:mm aa", Locale.ENGLISH)
    private val weekdayFormat = SimpleDateFormat("EEEE", Locale. ENGLISH)

    fun getDateTimeString(time: Int) : String = dateTimeFormat.format(time * 1000L)

    fun getWeekdayString(time: Int): String = weekdayFormat.format(time * 1000L)
}
