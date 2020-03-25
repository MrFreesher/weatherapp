package com.freesher.weatherappmd.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.freesher.weatherappmd.AppConstants
import com.freesher.weatherappmd.MainActivity
import com.freesher.weatherappmd.utils.Utils
import com.freesher.weatherappmd.databinding.FragmentDetailsBinding
import com.freesher.weatherappmd.utils.FragmentCommunication
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val arguments = arguments
        val binding = FragmentDetailsBinding.inflate(inflater,container,false)
        binding.temperatureLabel.label = "Temperature(C)"
        binding.temperatureLabel.value = arguments!!.getDouble("temperature").toString()
        binding.pressureLabel.label = "Pressure(hPa)"
        binding.pressureLabel.value = arguments!!.getDouble("pressure").toString()

        binding.sunriseLabel.label = "Sunrise time"
        binding.sunriseLabel.value = Utils.convertTimeStampToTime(arguments!!.getString("sunriseTime"))
        binding.sunsetLabel.label = "Sunset time"
        binding.sunsetLabel.value = Utils.convertTimeStampToTime(arguments!!.getString("sunsetTime"))
        binding.calculationDate = Utils.convertTimeStampToDate(arguments.getString("calculationDate"))
        binding.description = arguments.getString("description")
        binding.shortDescription = arguments.getString("shortDescription")
        val icon = arguments.getString("icon")
        val url = "${AppConstants.BASE_IMAGE_URL}/${icon}.png"
        Log.d("MyApp",url)
        Picasso.get().load(url).into(binding.iconIV)
        Log.d("MyApp",arguments.getString("calculationDate"))
        val root = binding.root

        val activity = activity as MainActivity
        activity.supportActionBar?.setDisplayShowHomeEnabled(true)
        activity.supportActionBar?.setHomeButtonEnabled(true)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        activity.supportActionBar?.title = arguments.getString("city")

        setHasOptionsMenu(true)
        return root

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            android.R.id.home -> {
                Log.d("MyApp","back")
                activity?.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
