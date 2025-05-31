package com.viveks.bmicalc.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.viveks.bmicalc.ui.theme.DarkBrownBackground
import com.viveks.bmicalc.ui.theme.DarkTextField
import com.viveks.bmicalc.ui.theme.ErrorRed
import com.viveks.bmicalc.ui.theme.GoldenButton
import com.viveks.bmicalc.ui.theme.LightBrownText
import com.viveks.bmicalc.viewmodel.BmiViewModel
import java.util.Locale

@Composable
fun BMICalcBox(
    modifier: Modifier = Modifier,
    viewModel: BmiViewModel
) {
    val heightInput = viewModel.heightInput
    val weightInput = viewModel.weightInput
    val result = viewModel.result

    Column(
        modifier.padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        OutlinedTextField(
            value = heightInput,
            onValueChange = { viewModel.heightInput = it },
            label = { Text("Height (cm)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = bmiTextFieldColors()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = weightInput,
            onValueChange = { viewModel.weightInput = it },
            label = { Text("Weight (kg)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = bmiTextFieldColors()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(
                onClick = { viewModel.calculateBMI() },
                modifier = Modifier
                    .height(50.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GoldenButton,
                    contentColor = Color.Black
                )
            ) {
                Text("Calculate")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = { viewModel.reset() },
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
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (result.value != 0.0f) {
            Text("Your BMI", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Text(
                String.format(Locale.ENGLISH, "%.2f", result.value),
                fontSize = 28.sp,
                color = Color.White
            )
            Text(result.category.description, fontSize = 18.sp, color = Color.White)
        }
    }
}

@Composable
fun bmiTextFieldColors() = TextFieldDefaults.colors(
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