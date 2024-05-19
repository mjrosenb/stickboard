package net.rosenbridge.stickboard

import android.graphics.Canvas
import android.graphics.Path

class LowerCase : StickAction {
    override fun doAction(parent: Stickboard) {
        val newState = when (val cur = parent.getCurrentState()) {
            StickboardState.LOWER -> StickboardState.LOWER
            StickboardState.UPPER -> StickboardState.LOWER
            StickboardState.UPPERLOCK -> StickboardState.LOWER
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
        // TODO: figure out how to draw symbols
        val size = 20
        val arrow = Path()
        arrow.moveTo(offX, offY)
        arrow.lineTo(offX - size, offY - size)
        arrow.lineTo(offX + size, offY - size)
        arrow.lineTo(offX, offY)
        canvas.drawPath(arrow, parent.nPaint)
    }
}
