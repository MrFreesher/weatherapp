package com.freesher.weatherappmd.search

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.freesher.weatherappmd.MainActivity
import com.freesher.weatherappmd.R
import com.freesher.weatherappmd.network.WeatherApi
import com.freesher.weatherappmd.network.WeatherRepository
import com.freesher.weatherappmd.utils.FragmentCommunication
import com.freesher.weatherappmd.utils.NetworkUtils
import kotlinx.android.synthetic.main.fragment_search.*

/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment() {
    private lateinit var repository: WeatherRepository
    private lateinit var client: WeatherApi
    private lateinit var searchViewModelFactory: SearchViewModelFactory
    private lateinit var searchViewModel: SearchViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        client = WeatherApi.invoke()
        repository = WeatherRepository(client)
        searchViewModelFactory = SearchViewModelFactory(repository)
        searchViewModel =
            ViewModelProvider(this, searchViewModelFactory).get(SearchViewModel::class.java)


        searchViewModel.weather.observe(this, Observer {
            if (it != null) {
                val bundle = Bundle()
                bundle.putString("calculationDate", it.calculationDate)
                bundle.putString("description", it.weatherInfo[0].name)
                bundle.putString("shortDescription", it.weatherInfo[0].description)
                bundle.putString("icon", it.weatherInfo[0].icon)
                bundle.putDouble("temperature", it.weatherDetails.temperature)
                bundle.putDouble("pressure", it.weatherDetails.pressure)
                bundle.putString("sunriseTime", it.sunDetails.sunriseDate)
                bundle.putString("sunsetTime", it.sunDetails.sunsetDate)
                bundle.putString("city", it.city)


                val activity = activity as FragmentCommunication

                activity.replaceFragment("details", bundle)
            }

        })
        searchViewModel.loading.observe(this, Observer {
            if (it == true) {
                progressBar.visibility = View.VISIBLE
                Log.d("MyAPP", "Loading on")
            } else if (it == false) {
                progressBar.visibility = View.GONE
                Log.d("MyAPP", "Loading off")

            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activity = activity as MainActivity
        activity.supportActionBar?.title = "Weather App"
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        activity.supportActionBar?.setDisplayShowHomeEnabled(false)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchBtn.setOnClickListener {
            val city = cityInput.text.toString()
            if (validateInput(city)) {
                if (NetworkUtils.checkInternetAvailability(context!!.applicationContext)) {


                    searchViewModel.getWeather(city)
                } else {
                    AlertDialog.Builder(context)
                        .setMessage("App required Internet Connection to work")

                        .setNeutralButton(
                            "Ok"

                        ) { dialog, _ -> dialog?.dismiss() }.create().show()
                }
            } else {
                cityInput.error = "This field can not be empty"
            }
        }

    }

    fun validateInput(input: String): Boolean {
        if (TextUtils.isEmpty(input)) {
            return false
        }
        return true

    }


}
