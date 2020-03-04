package com.freesher.weatherappmd.network

import com.freesher.weatherappmd.data.Weather

class WeatherRepository(private val client:WeatherApi) {
    suspend fun getWeatherByCity(city:String):Weather{
        return client.getWeather(city)
    }
}