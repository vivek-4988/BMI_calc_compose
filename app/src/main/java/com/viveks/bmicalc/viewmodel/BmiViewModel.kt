package com.viveks.bmicalc.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.viveks.bmicalc.model.BmiCategory
import com.viveks.bmicalc.model.BmiResult
import kotlin.math.pow


class BmiViewModel : ViewModel() {

    var heightInput by mutableStateOf("")
    var weightInput by mutableStateOf("")
    var result by mutableStateOf(BmiResult(0.0f, BmiCategory.UNDEFINED))
        private set

    fun calculateBMI() {
        val height = heightInput.toFloatOrNull()
        val weight = weightInput.toFloatOrNull()

        result = if (height != null && weight != null && height > 0 && weight > 0) {
            val bmi = weight / (height / 100).pow(2)
            BmiResult(
                value = bmi,
                category = when {
                    bmi < 18.5 -> BmiCategory.UNDERWEIGHT
                    bmi < 24.9 -> BmiCategory.NORMAL
                    bmi < 29.9 -> BmiCategory.OVERWEIGHT
                    else -> BmiCategory.OBESE
                }
            )
        } else {
            BmiResult(0.0f, BmiCategory.UNDEFINED)
        }
    }

    fun reset() {
        heightInput = ""
        weightInput = ""
        result = BmiResult(0.0f, BmiCategory.UNDEFINED)
    }
}