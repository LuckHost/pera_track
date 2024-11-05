package com.luckhost.peratrack.presentation.mainScreen.chartWithListFragment

import androidx.core.graphics.ColorUtils
import kotlin.math.absoluteValue

/**
 * Source
 * https://habr.com/ru/articles/730924/
 *
 * @property value - The value of the part. Like the price of one item in the receipt.
 * @property percentOfCircle - percent of diagram. Should be between 0 and 100
 * @property color - Color of stroke of this part
 */
data class PieChartElement(
    var itemName: String,
    var value: Float,
    var color: Int = -1
) {
    var percentOfCircle: Float = -1f

    init {
        // Calculating the color by the name if it is not stated
        if (color == -1) {
            color = generateColorForName(itemName)
        }
    }

    private fun generateColorForName(itemName: String): Int {
        // Get index from entered string to subsequent steps
        val index = itemName.hashCode().absoluteValue

        // Hue from 0 to 360
        // Using the golden ratio for uniform distribution
        val hue = (index * 137.508).toFloat() % 360
        val saturation = 0.7f
        val lightness = 0.6f

        // Converting in to the HSL color
        return ColorUtils.HSLToColor(floatArrayOf(hue, saturation, lightness))
    }
}
