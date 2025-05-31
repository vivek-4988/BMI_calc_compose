package com.viveks.bmicalc.model

enum class BmiCategory(val description: String) {
    UNDERWEIGHT("Underweight"),
    NORMAL("Normal weight"),
    OVERWEIGHT("Overweight"),
    OBESE("Obese"),
    UNDEFINED("Please enter valid values")
}

// Data class to hold the BMI result
data class BmiResult(
    val value: Float,
    val category: BmiCategory
)