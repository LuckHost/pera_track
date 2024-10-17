package com.luckhost.peratrack.presentation.mainScreen.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class PieChartView(
    context: Context,
    attributeSet: AttributeSet
) : View(context, attributeSet) {

    companion object {
        /* Ширина строки круговой диаграммы*/
        const val DEFAULT_STROKE_WIDTH = 5f
    }

    // The map of parts of a diagram
    private var sectorsList: List<PieChartElement> = listOf()
    private val paint = Paint()

    init {
        paint.style = Paint.Style.FILL
        paint.color = Color.CYAN
        paint.strokeWidth = DEFAULT_STROKE_WIDTH
    }

    // Updating the chart content
    fun updateSectorsList(newList: List<PieChartElement>) {
        sectorsList = newList

        // Bringing the sum of all "percentOfCircle" of elements to 360
        val percentSum = sectorsList.sumOf { it.percentOfCircle.toDouble() }
        if (percentSum < 360) {
            val valueToAdd: Float = (360 - percentSum).toFloat() / sectorsList.count()
            sectorsList.map {
                it.percentOfCircle += valueToAdd
            }
        } else if (percentSum > 360) {
            val valueToSubtract: Float = (percentSum - 360).toFloat() / sectorsList.count()
            sectorsList.map {
                it.percentOfCircle -= valueToSubtract
            }
        }

        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val center = width / 2f
        val radius = width.coerceAtMost(height) / 2f
        var startAngle = 0f

        for (i in sectorsList) {
            // Calculate color from name
            paint.color = i.itemName.hashCode()


            canvas.drawArc(
                center - radius,
                center - radius,
                center + radius,
                center + radius,
                startAngle,
                i.percentOfCircle,
                true,
                paint
            )

            startAngle += i.percentOfCircle
        }
    }
}