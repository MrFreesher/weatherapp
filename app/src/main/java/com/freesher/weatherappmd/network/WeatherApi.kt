package com.freesher.weatherappmd.network

import com.freesher.weatherappmd.data.Weather
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/weather")
    fun getWeather(@Query("q") city: String) : Weather
}