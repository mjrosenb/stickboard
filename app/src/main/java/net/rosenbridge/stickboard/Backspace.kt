package net.rosenbridge.stickboard

import android.graphics.Canvas

class Backspace : StickAction {
    override fun doAction(parent: Stickboard) {
parent.getCallback().onDel()

    }

    override fun drawLabel(
        parent: Stickboard,
        canvas: Canvas,
        offX: Float,
        offY: Float,
        isMain: Boolean
    ) {
        val paint = if (isMain)  parent.pPaint else parent.nPaint
        drawText("⌫", offX, offY, canvas, paint)

    }
}