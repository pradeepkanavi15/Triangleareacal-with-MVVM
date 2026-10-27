
package com.example.areacalculatormvvm.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.areacalculatormvvm.utils.AreaCalculator

class AreaCalculatorViewModel : ViewModel() {

    var selectedShape by mutableStateOf("Rectangle")
        private set

    var firstMeasurement by mutableStateOf("")
        private set

    var secondMeasurement by mutableStateOf("")
        private set

    var calculatedArea by mutableStateOf("")
        private set

    fun updateShape(shape: String) {
        selectedShape = shape
        firstMeasurement = ""
        secondMeasurement = ""
        calculatedArea = ""
    }

    fun updateFirstMeasurement(value: String) {
        firstMeasurement = value
    }

    fun updateSecondMeasurement(value: String) {
        secondMeasurement = value
    }

    fun calculateArea() {
        calculatedArea = when (selectedShape) {
            "Rectangle" -> {
                val length = firstMeasurement.toDoubleOrNull() ?: 0.0
                val width = secondMeasurement.toDoubleOrNull() ?: 0.0
                val area = AreaCalculator.calculateRectangleArea(length, width)
                if (length == 0.0 || width == 0.0) {
                    "Please enter valid values"
                } else {
                    "Area of Rectangle = $area"
                }
            }
            "Circle" -> {
                val radius = firstMeasurement.toDoubleOrNull() ?: 0.0
                val area = AreaCalculator.calculateCircleArea(radius)
                if (radius == 0.0) {
                    "Please enter valid radius"
                } else {
                    "Area of Circle = ${String.format("%.2f", area)}"
                }
            }
            else -> {
                val base = firstMeasurement.toDoubleOrNull() ?: 0.0
                val height = secondMeasurement.toDoubleOrNull() ?: 0.0
                val area = AreaCalculator.calculateTriangleArea(base, height)
                if (base == 0.0 || height == 0.0) {
                    "Please enter valid values"
                } else {
                    "Area of Triangle = $area"
                }
            }
        }
    }

    fun getInputLabel(): String {
        return when (selectedShape) {
            "Rectangle" -> "Length"
            "Circle" -> "Radius"
            else -> "Base"
        }
    }

    fun getSecondInputLabel(): String {
        return if (selectedShape == "Rectangle") "Width" else "Height"
    }

    fun isCircle(): Boolean = selectedShape == "Circle"
}