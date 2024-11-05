package com.luckhost.peratrack.presentation.mainScreen.chartWithListFragment

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

class PieChartView(
    context: Context,
    attributeSet: AttributeSet
) : View(context, attributeSet) {

    companion object {
        /* Stroke of pie chart element width */
        const val DEFAULT_STROKE_WIDTH = 45f
    }

    // The map of parts of a diagram
    private var sectorsList: List<PieChartElement> = listOf()
    private var totalAmount: Float = 0f
    private val chartPaint = Paint()

    private val textPaint = Paint()
    // is needed to draw an amount in the center of the circle
    private val textRect = Rect()

    init {
        chartPaint.style = Paint.Style.STROKE
        chartPaint.color = Color.CYAN
        chartPaint.strokeWidth = DEFAULT_STROKE_WIDTH
        chartPaint.isDither = true

        textPaint.color = Color.WHITE
        textPaint.textSize = 80f

        // preview data
        if (isInEditMode) {
            totalAmount = 2264.3f
            updateSectorsList(listOf(
                PieChartElement("Командор", 1902f),
                PieChartElement("Красный яр", 362f)
            ))

            setBackgroundColor(Color.parseColor("#E0E0E0"))
        }
    }

    // Updating the chart content
    fun updateSectorsList(newList: List<PieChartElement>) {
        sectorsList = newList

        // Bringing the sum of all "percentOfCircle" of elements to 360
        totalAmount = sectorsList.sumOf { it.value.toDouble() }.toFloat()

        sectorsList.map {
            it.percentOfCircle = 360 * (it.value / totalAmount)
        }

        invalidate()
    }

    private fun drawAmount(canvas: Canvas) {
        // draw an integer if there are no signs after the dot
        // and a fractional number if there are
        val text = if (totalAmount % 1.0f == 0.0f) {
            totalAmount.toInt().toString()
        } else {
            totalAmount.toString()
        }

        val centerX = width/2f
        val centerY= height/2f

        textPaint.getTextBounds(text, 0, text.length, textRect)

        val textWidth = textPaint.measureText(text)
        val textHeight = textRect.height()

        canvas.drawText(
            text,
            centerX - (textWidth / 2f),
            centerY + (textHeight /2f),
            textPaint
        )
    }

    private fun drawCircle(canvas: Canvas) {
        val centerX = width/2f
        val centerY= height/2f

        val radius = width.coerceAtMost(height) / 2f - DEFAULT_STROKE_WIDTH
        var startAngle = 0f

        for (i in sectorsList) {
            // Calculate color from name
            chartPaint.color = i.color

            canvas.drawArc(
                centerX - radius,
                centerY - radius,
                centerX + radius,
                centerY + radius,
                startAngle,
                i.percentOfCircle,
                false,
                chartPaint
            )

            startAngle += i.percentOfCircle
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCircle(canvas)
        drawAmount(canvas)
    }
}