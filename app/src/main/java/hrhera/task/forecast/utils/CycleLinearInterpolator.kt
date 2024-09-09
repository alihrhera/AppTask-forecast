package hrhera.task.forecast.utils

import android.view.animation.Interpolator

class CycleLinearInterpolator(private val cycle: Int) : Interpolator {
    override fun getInterpolation(input: Float): Float {
        // Emulate a linear interpolation with 7 cycles using sine function
        return kotlin.math.sin(cycle * Math.PI * input).toFloat()
    }
}