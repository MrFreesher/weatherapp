package com.freesher.weatherappmd.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.freesher.weatherappmd.MainActivity
import com.freesher.weatherappmd.utils.Utils
import com.freesher.weatherappmd.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val arguments = arguments
        val binding = FragmentDetailsBinding.inflate(inflater,container,false)
        binding.temperatureLabel.label = "Temperature"
        binding.temperatureLabel.value = arguments!!.getDouble("temperature").toString()
        binding.pressureLabel.label = "Pressure"
        binding.pressureLabel.value = arguments!!.getDouble("pressure").toString()

        binding.sunriseLabel.label = "Sunrise time"
        binding.sunriseLabel.value = Utils.convertTimeStampToDate(arguments!!.getString("sunriseTime"))
        binding.sunsetLabel.label = "Sunset time"
        binding.sunsetLabel.value = Utils.convertTimeStampToDate(arguments!!.getString("sunsetTime"))
        binding.calculationDate = Utils.convertTimeStampToDate(arguments.getString("calculationDate"))

        Log.d("MyApp",arguments.getString("calculationDate"))
        val root = binding.root

        val activity = activity as MainActivity
        activity.supportActionBar?.setDisplayShowHomeEnabled(true)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)


        // Inflate the layout for this fragment
        return root

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val activity = activity as? MainActivity
        return when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
