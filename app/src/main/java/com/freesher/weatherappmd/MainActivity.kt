package com.freesher.weatherappmd

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.freesher.weatherappmd.details.DetailsFragment

import com.freesher.weatherappmd.search.SearchFragment
import com.freesher.weatherappmd.utils.FragmentCommunication

class MainActivity : AppCompatActivity(), FragmentCommunication {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       replaceFragment("search",null)
    }

    override fun replaceFragment(type: String,arguments:Bundle?) {
        if (type == "search") {
            val fragment = SearchFragment()

            supportFragmentManager.beginTransaction().replace(R.id.fragContainer, fragment).addToBackStack(null).commit()
        } else if (type == "details") {
            val fragment = DetailsFragment()
            fragment.arguments = arguments

            supportFragmentManager.beginTransaction().replace(R.id.fragContainer, fragment).addToBackStack(null).commit()
        }
    }


}
