package com.viveks.bmicalc.utils

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified T : ViewModel> ComponentActivity.lazyViewModel() = lazy {
    ViewModelProvider(this)[T::class.java]
}