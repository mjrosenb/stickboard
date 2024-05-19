package net.rosenbridge.stickboard

import android.graphics.Canvas

class Nums : StickAction {
    override fun doAction(parent: Stickboard) {
        parent.setCurrentState(StickboardState.NUMS)
    }

    override fun drawLabel(
        parent: Stickboard,
        canvas: Canvas,
        offX: Float,
        offY: Float,
        isMain: Boolean
    ) {
        drawText("123", offX, offY,canvas, parent.nPaint)
    }

}