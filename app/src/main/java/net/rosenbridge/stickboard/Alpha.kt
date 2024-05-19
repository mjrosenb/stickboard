package net.rosenbridge.stickboard

import android.graphics.Canvas

class Alpha : StickAction {
    override fun doAction(parent: Stickboard) {
        parent.setCurrentState(StickboardState.LOWER)
    }


    override fun drawLabel(
        parent: Stickboard,
        canvas: Canvas,
        offX: Float,
        offY: Float,
        isMain: Boolean
    ) {
        drawText("ABC", offX, offY, canvas, parent.nPaint)
    }

}