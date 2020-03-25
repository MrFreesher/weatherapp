package com.freesher.weatherappmd.utils

import android.os.Bundle

interface FragmentCommunication {
    fun replaceFragment(type:String,arguments: Bundle?)
}