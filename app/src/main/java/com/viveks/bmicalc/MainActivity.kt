package com.viveks.bmicalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults.colors
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.viveks.bmicalc.ui.theme.BMITheme
import com.viveks.bmicalc.ui.theme.DarkBrownBackground
import com.viveks.bmicalc.ui.theme.DarkTextField
import com.viveks.bmicalc.ui.theme.ErrorRed
import com.viveks.bmicalc.ui.theme.GoldenButton
import com.viveks.bmicalc.ui.theme.LightBrownText

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BMITheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BMIScreen()
                }
            }
        }
    }

    @Composable
    fun BMICalcBox(modifier: Modifier = Modifier) {
        var weightInput by remember { mutableStateOf("") }
        var heightInput by remember { mutableStateOf("") }
        var bmiResult by remember { mutableStateOf("") }
        var category by remember { mutableStateOf("") }

        Column(
            modifier.padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            OutlinedTextField(
                value = heightInput,
                onValueChange = { heightInput = it },
                label = { Text("Height (cm)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                colors = colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = LightBrownText,
                    focusedContainerColor = DarkTextField,
                    unfocusedContainerColor = DarkTextField,
                    disabledContainerColor = DarkTextField,
                    cursorColor = GoldenButton,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    errorIndicatorColor = ErrorRed,
                    focusedLabelColor = GoldenButton,
                    unfocusedLabelColor = LightBrownText,
                    errorLabelColor = ErrorRed
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = weightInput,
                onValueChange = { weightInput = it },
                label = { Text("Weight (kg)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                colors = colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = LightBrownText,
                    focusedContainerColor = DarkTextField,
                    unfocusedContainerColor = DarkTextField,
                    disabledContainerColor = DarkTextField,
                    cursorColor = GoldenButton,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    errorIndicatorColor = ErrorRed,
                    focusedLabelColor = GoldenButton,
                    unfocusedLabelColor = LightBrownText,
                    errorLabelColor = ErrorRed
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val height = heightInput.toFloatOrNull()
                    val weight = weightInput.toFloatOrNull()

                    if (height != null && weight != null) {
                        val bmi = weight / ((height / 100) * (height / 100))
                        bmiResult = "%.2f".format(bmi)

                        category = when {
                            bmi < 18.5 -> "Underweight"
                            bmi < 24.9 -> "Normal weight"
                            bmi < 29.9 -> "Overweight"
                            else -> "Obese"
                        }
                    } else {
                        bmiResult = ""
                        category = ""
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GoldenButton,
                    contentColor = Color.Black
                )
            ) {
                Text("Calculate")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    heightInput = ""
                    weightInput = ""
                    bmiResult = ""
                    category = ""
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 5.dp,
                    pressedElevation = 8.dp
                ),
                shape = RoundedCornerShape(30.dp),
                border = BorderStroke(1.dp, Color.Yellow),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = DarkBrownBackground,
                    contentColor = Color.White
                )
            ) {
                Text("Reset")
            }

            if (bmiResult.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Your BMI",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    bmiResult,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
                Text(category, fontSize = 18.sp, color = Color.White)
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun BMIScreen() {
        Scaffold(
            topBar = { TopBar() },
            bottomBar = { BottomNavigationBar() },
            containerColor = Color(0xFF1E180E),
            content = { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    BMICalcBox()
                }
            }
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopBar() {
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF1E180E),
                titleContentColor = Color.White
            ),
            title = {
                Text(
                    "BMI Calculator",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            },
            navigationIcon = {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            },
        )
    }

    @Composable
    fun BottomNavigationBar() {
        NavigationBar() {
            NavigationBarItem(
                selected = true,
                onClick = { /*TODO*/ },
                icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                label = { Text("Home") }
            )
            NavigationBarItem(
                selected = false,
                onClick = { /* TODO */ },
                icon = { Icon(Icons.Outlined.Favorite, contentDescription = "Heart") },
                label = { Text("Heart") }
            )
            NavigationBarItem(
                selected = false,
                onClick = { /* TODO */ },
                icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                label = { Text("Profile") }
            )
        }
    }


}



