package com.freesher.weatherappmd.utils

import java.text.SimpleDateFormat
import java.util.*
object Utils {
    fun convertTimeStampToTime(timestamp: String?): String {
        val sdf = SimpleDateFormat(" HH:mm")
        val netDate = Date(timestamp!!.toLong()*1000)
        val date = sdf.format(netDate)
        return date
    }
    fun convertTimeStampToDate(timestamp: String?): String {
        val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm")
        val netDate = Date(timestamp!!.toLong()*1000)
        val date = sdf.format(netDate)
        return date
    }
}