package com.viveks.bmicalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.viveks.bmicalc.ui.BMIScreen
import com.viveks.bmicalc.ui.theme.BMITheme
import com.viveks.bmicalc.utils.lazyViewModel
import com.viveks.bmicalc.viewmodel.BmiViewModel

class MainActivity : ComponentActivity() {

    private val vm by lazyViewModel<BmiViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BMITheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BMIScreen(vm)
                }
            }
        }
    }
}



