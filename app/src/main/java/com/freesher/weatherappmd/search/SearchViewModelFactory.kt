package com.freesher.weatherappmd.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.freesher.weatherappmd.network.WeatherRepository

class SearchViewModelFactory(private val repository: WeatherRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return SearchViewModel(repository) as T


    }
}