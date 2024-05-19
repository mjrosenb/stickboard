package net.rosenbridge.stickboard

import android.graphics.Canvas

class Done : StickAction {
    override fun doAction(parent: Stickboard) {
parent.getCallback().onDone()
    }

    override fun drawLabel(
        parent: Stickboard,
        canvas: Canvas,
        offX: Float,
        offY: Float,
        isMain: Boolean
    ) {
        val paint = if (isMain)  parent.pPaint else parent.nPaint
        drawText("Done", offX, offY, canvas, paint)
    }
}