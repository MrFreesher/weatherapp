package com.freesher.weatherappmd

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.freesher.weatherappmd.utils.FragmentCommunication

class MainActivity : AppCompatActivity(), FragmentCommunication {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = SearchFragment()

       replaceFragment("search")
    }

    override fun replaceFragment(type: String) {
        if (type == "search") {
            val fragment = SearchFragment()

            supportFragmentManager.beginTransaction().replace(R.id.fragContainer, fragment).commit()
        } else if (type == "details") {
            val fragment = DetailsFragment()

            supportFragmentManager.beginTransaction().replace(R.id.fragContainer, fragment).commit()
        }
    }


}
