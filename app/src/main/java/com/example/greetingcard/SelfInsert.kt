package com.example.greetingcard

import android.graphics.Canvas

class SelfInsert(private val char : Char) : StickAction {
    private val chars = char.toString()
    override fun doAction(parent: Stickboard) {
        parent.getCallback().onChar(char)
        when (char) {
            '.', '!', '?' -> if (parent.getCurrentState() == StickboardState.LOWER) {
                parent.setCurrentState(StickboardState.UPPER)
            }
            else -> {
            if (char.isLetter() && parent.getCurrentState() == StickboardState.UPPER) {
                parent.setCurrentState(StickboardState.LOWER)
            }
        }
        }
    }

    override fun drawLabel(parent: Stickboard, canvas: Canvas, offX: Float, offY: Float, isMain : Boolean) {
        val paint = if (isMain)  parent.pPaint else parent.nPaint
        drawText(chars, offX, offY, canvas, paint)
    }
}