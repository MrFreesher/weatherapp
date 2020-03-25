package com.freesher.weatherappmd.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object NetworkUtils {
    fun checkInternetAvailability(applicationContext:Context):Boolean{
        var result = false
        val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        connectivityManager?.let {
            it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {

                result = when{

                    this.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    this.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    else -> false

                }
            }
        }
        return result
    }
}