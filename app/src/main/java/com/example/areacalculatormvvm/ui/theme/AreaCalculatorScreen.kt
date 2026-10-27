
package com.example.areacalculatormvvm.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.areacalculatormvvm.viewmodel.AreaCalculatorViewModel

@Composable
fun AreaCalculatorScreen(
    viewModel: AreaCalculatorViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "App to Calculate Area",
            fontSize = 28.sp,
            modifier = Modifier.padding(top = 30.dp, bottom = 8.dp)
        )

        // Subtitle
        Text(
            text = "Select Shape",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // Shape Selection Buttons
        ShapeSelectionButtons(viewModel)

        Spacer(modifier = Modifier.height(24.dp))

        // Input fields
        InputFields(viewModel)

        Spacer(modifier = Modifier.height(24.dp))

        // Calculate Button
        CalculateButton(viewModel)

        Spacer(modifier = Modifier.height(24.dp))

        // Result Display
        ResultDisplay(viewModel)

        // Developer Credit
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Developed by Pradeep Kanavi",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}

@Composable
fun ShapeSelectionButtons(viewModel: AreaCalculatorViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        ShapeButton(
            text = "Rectangle",
            isSelected = viewModel.selectedShape == "Rectangle",
            onClick = { viewModel.updateShape("Rectangle") }
        )

        Spacer(modifier = Modifier.width(8.dp))

        ShapeButton(
            text = "Circle",
            isSelected = viewModel.selectedShape == "Circle",
            onClick = { viewModel.updateShape("Circle") }
        )

        Spacer(modifier = Modifier.width(8.dp))

        ShapeButton(
            text = "Triangle",
            isSelected = viewModel.selectedShape == "Triangle",
            onClick = { viewModel.updateShape("Triangle") }
        )
    }
}

@Composable
fun ShapeButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Text(text)
    }
}

@Composable
fun InputFields(viewModel: AreaCalculatorViewModel) {
    OutlinedTextField(
        value = viewModel.firstMeasurement,
        onValueChange = { viewModel.updateFirstMeasurement(it) },
        label = { Text(viewModel.getInputLabel()) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.fillMaxWidth()
    )

    if (!viewModel.isCircle()) {
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = viewModel.secondMeasurement,
            onValueChange = { viewModel.updateSecondMeasurement(it) },
            label = { Text(viewModel.getSecondInputLabel()) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun CalculateButton(viewModel: AreaCalculatorViewModel) {
    Button(
        onClick = { viewModel.calculateArea() },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Calculate Area")
    }
}

@Composable
fun ResultDisplay(viewModel: AreaCalculatorViewModel) {
    if (viewModel.calculatedArea.isNotEmpty()) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Text(
                text = viewModel.calculatedArea,
                fontSize = 20.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AreaCalculatormvvmPreview() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            AreaCalculatorScreen()
        }
    }
}