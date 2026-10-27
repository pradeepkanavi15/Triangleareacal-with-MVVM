package com.example.areacalculatormvvm.utils

import kotlin.math.PI

object AreaCalculator {

    fun calculateRectangleArea(length: Double, width: Double): Double {
        return length * width
    }

    fun calculateCircleArea(radius: Double): Double {
        return PI * radius * radius
    }

    fun calculateTriangleArea(base: Double, height: Double): Double {
        return 0.5 * base * height
    }
}