package com.freesher.weatherappmd.data

import com.google.gson.annotations.SerializedName

class Weather(
    @SerializedName("date")
    val calculationDate: String,
    @SerializedName("name")
    val city: String,
    @SerializedName("weather")
    val weatherInfo: WeatherInfo,
    @SerializedName("main")
    val weatherDetails: WeatherDetails,
    @SerializedName("sys")
    val sunDetails: SunDetails
)

class SunDetails(
    @SerializedName("sunrise")
    val sunriseDate: String,
    @SerializedName("sunset")
    val sunsetDate: String
)


class WeatherDetails(
    @SerializedName("temp")
    val temperature: Double,
    @SerializedName("pressure")
    val pressure: Double

)

data class WeatherInfo(
    @SerializedName("main")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String
)