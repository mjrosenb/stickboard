package net.rosenbridge.stickboard

import android.graphics.Canvas

class Stick(val actions : Map<StickPos, StickAction>, val l : Int, val t : Int, val  w : Int, val h : Int, val parent : Stickboard) {
    constructor(chars : Map<StickPos, Char>, l : Int, t : Int, w : Int, h : Int, parent : Stickboard, ignored : Int) :
            this(chars.mapValues { e -> SelfInsert(e.value) }, l, t, w, h, parent)
    fun toUpper() : Stick {
        return Stick(actions.mapValues { it.value.toUpper() }, l, t, w, h, parent)
    }
    fun onRender(canvas : Canvas) {
        val left = (parent.xOff + l * parent.stickScale).toFloat()
        val right = ( left + w * parent.stickScale - parent.padding).toFloat()
        val mx = left + (w * parent.stickScale - parent.padding) / 2
        val dx = parent.stickScale / 3
        val top = (parent.yOff + t * parent.stickScale).toFloat()
        val bottom = (top + h * parent.stickScale - parent.padding).toFloat()
        val my =  top + (h * parent.stickScale - parent.padding) / 2
        val dy = parent.stickScale / 3
        canvas.drawRect(left, top, right, bottom, parent.fPaint)
        canvas.drawRect(left, top, right, bottom, parent.sPaint)
        actions.forEach { e->
            val p = e.key
            val action = e.value
            action.drawLabel(parent, canvas, mx + p.x * dx, my + p.y * dy, p.isMain())
        }

    }
    companion object {
        fun merge(l : Stick?, r : Stick? ) : Stick? {
            if (l == null)
                return r
            if (r == null)
                return l
            var bothKeys = l.actions.keys.union(r.actions.keys);
            var bothMap : Map<StickPos, StickAction> = bothKeys.map { Pair(it, l.actions.getOrDefault(it,
                r.actions[it]
            )!!) }.toMap();
            return Stick(bothMap, l.l, l.t, l.w, l.h, l.parent)
        }
    }
}