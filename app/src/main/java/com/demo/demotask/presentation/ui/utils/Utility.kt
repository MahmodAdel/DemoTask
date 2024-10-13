package com.demo.demotask.presentation.ui.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


fun getCurrentTime(): String {
    var date = Date()
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS", Locale.ENGLISH)
    simpleDateFormat.timeZone = TimeZone.getTimeZone("GMT+2:00")
    return simpleDateFormat.format(date)

}