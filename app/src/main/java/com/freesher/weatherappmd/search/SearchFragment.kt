package com.freesher.weatherappmd.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.freesher.weatherappmd.R
import com.freesher.weatherappmd.network.WeatherApi
import com.freesher.weatherappmd.network.WeatherRepository
import kotlinx.android.synthetic.main.fragment_search.*

/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment() {
    private lateinit var repository: WeatherRepository
    private lateinit var client:WeatherApi
    private lateinit var searchViewModelFactory: SearchViewModelFactory
    private lateinit var searchViewModel: SearchViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        client = WeatherApi.invoke()
        repository = WeatherRepository(client)
        searchViewModelFactory = SearchViewModelFactory(repository)
        searchViewModel = ViewModelProvider(this,searchViewModelFactory).get(SearchViewModel::class.java)

        searchBtn.setOnClickListener {
            val city = cityInput.text.toString()
            searchViewModel.getWeather(city)

        }

        searchViewModel.loading.observe(this, Observer {
            if(it == true){
                progressBar.visibility = View.VISIBLE
                Log.d("MyAPP","Loading on")
            }else{
                progressBar.visibility = View.GONE
                Log.d("MyAPP","Loading off")
            }
        })

    }



}
