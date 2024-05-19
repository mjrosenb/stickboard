package net.rosenbridge.stickboard

import android.graphics.Canvas
import android.graphics.Path

class RaiseCase : StickAction {
    override fun doAction(parent: Stickboard) {
        val newState = when (val cur = parent.getCurrentState()) {
            StickboardState.LOWER -> StickboardState.UPPER
            StickboardState.UPPER -> StickboardState.UPPERLOCK
            StickboardState.UPPERLOCK -> StickboardState.UPPER
            else -> cur
        }
        parent.setCurrentState(newState)
    }

    override fun drawLabel(
        parent: Stickboard,
        canvas: Canvas,
        offX: Float,
        offY: Float,
        isMain: Boolean
    ) {
        val size = 20
        val arrow = Path()
        arrow.moveTo(offX, offY)
        arrow.lineTo(offX - size, offY + size)
        arrow.lineTo(offX + size, offY + size)
        arrow.lineTo(offX, offY)
        canvas.drawPath(arrow, parent.nPaint)

    }

}