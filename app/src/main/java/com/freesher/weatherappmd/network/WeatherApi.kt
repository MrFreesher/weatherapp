package com.freesher.weatherappmd.network

import com.freesher.weatherappmd.AppConstants
import com.freesher.weatherappmd.data.Weather
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    fun getWeather(@Query("q") city: String) : Weather

    companion object{
        operator fun invoke():WeatherApi{
            val requestInterceptor = Interceptor { chain->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("appid",AppConstants.API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()
            return  Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherApi::class.java)
        }
    }
}