package com.freesher.weatherappmd.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.freesher.weatherappmd.data.Weather
import com.freesher.weatherappmd.network.WeatherRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: WeatherRepository):ViewModel() {


    private var _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    private var _weather:MutableLiveData<Weather> = MutableLiveData()

    val loading:LiveData<Boolean>
    get() = _loading

    val weather:LiveData<Weather>
    get() = _weather


    fun getWeather(city:String){
        GlobalScope.launch {
            _loading.postValue(true)
            val actualWeather = repository.getWeatherByCity(city)
            _weather.postValue(actualWeather)
            _loading.postValue(false)

        }
    }
}