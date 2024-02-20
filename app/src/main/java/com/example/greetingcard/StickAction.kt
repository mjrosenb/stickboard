package com.example.greetingcard

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect

interface StickAction {
    fun doAction(parent: Stickboard)
    fun drawLabel(parent: Stickboard, canvas: Canvas, offX: Float, offY: Float, isMain: Boolean)

    fun drawText(str: String, offX: Float, offY: Float, canvas: Canvas, paint: Paint) {
        var bounds: Rect = Rect(0,0,0,0)
        paint.getTextBounds(str, 0, str.length, bounds)
        val dx = bounds.exactCenterX()
        val dy = bounds.exactCenterY()
        canvas.drawText(str, offX - dx, offY - dy, paint)
    }
}