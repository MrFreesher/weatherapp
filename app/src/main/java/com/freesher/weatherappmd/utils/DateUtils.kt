package com.freesher.weatherappmd.utils

import java.text.SimpleDateFormat
import java.util.*
object DateUtils {
    fun convertTimeStampToTime(timestamp: String?): String {
        val sdf = SimpleDateFormat(" HH:mm")
        val netDate = Date(timestamp!!.toLong()*1000)
        return sdf.format(netDate)
    }
    fun convertTimeStampToDate(timestamp: String?): String {
        val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm")
        val netDate = Date(timestamp!!.toLong()*1000)
        return sdf.format(netDate)
    }
}