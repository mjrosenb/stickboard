package com.example.greetingcard

import android.graphics.Canvas
import android.graphics.Paint
import android.util.Log
import android.view.MotionEvent
import kotlin.math.min
import com.example.greetingcard.StickPos.*
import kotlin.math.abs
import kotlin.math.sign

// 6884
class Stickboard(val parent : KbdView) {
    interface OnStickEventListener {
        fun onChar(code: Char)
        fun onDel()
        fun onDone()
    }

    class EmptyListener : OnStickEventListener {
        override fun onChar(code: Char) {
            Log.i("Kbd", "ignoring onChar($code)")
        }

        override fun onDel() {
            Log.i("Kbd", "ignoring onDel()")
        }
        override fun onDone() {
            Log.i("Kbd", "ignoring onDone()")
        }
    }


    private var mCallback: Stickboard.OnStickEventListener = Stickboard.EmptyListener()
    fun setCallback(cb: Stickboard.OnStickEventListener) {
        mCallback = cb
    }
    fun getCallback() : Stickboard.OnStickEventListener {
        return mCallback
    }
    val upperSticks: Collection<Stick> = listOf(
        Stick(mapOf(C to 'A', R to '-', BL to '$', BR to 'V'), 0, 0, 1, 1, this, 0),
        Stick(mapOf(C to 'N', TL to '`', T to '^', TR to '’', R to '!', BL to '/', B to 'L', BR to '\\'), 1, 0, 1, 1, this, 0),
        Stick(mapOf(C to 'I', L to '?', BL to 'X', B to '='), 2, 0, 1, 1, this, 0),

        Stick(mapOf(C to 'H', TL to '{', TR to '%', L to '(', R to 'K', BL to '[', BR to '_'), 0, 1, 1, 1, this, 0),
        Stick(mapOf(C to 'O', TL to 'Q', T to 'U', TR to 'P', L to 'C', R to 'B', BL to 'G', B to 'D', BR to 'J'), 1, 1, 1, 1, this, 0),
        Stick(mapOf(C to SelfInsert('R'), TL to SelfInsert('|'), T to RaiseCase(), TR to SelfInsert('}'), L to SelfInsert('M'), R to SelfInsert(')'), BL to SelfInsert('@'), B to LowerCase(), BR to SelfInsert(']')), 2, 1, 1, 1, this),
        Stick(mapOf(C to Nums()), 3, 1, 1, 1, this),

        Stick(mapOf(C to 'T', TL to '~', TR to 'Y', L to '<', R to '*'), 0, 2, 1, 1, this, 0),
        Stick(mapOf(C to 'E', TL  to '"', T to 'W', TR to '\'', R to 'Z', BL to ',', B to '.'), 1, 2, 1, 1, this, 0),
        Stick(mapOf(C to 'S', TL to 'F', T to '&', TR to '°', L to '&', R to '>'), 2, 2, 1, 1, this, 0),
        Stick(mapOf(C to Backspace()), 3, 2, 1, 1, this),

        Stick(mapOf(C to ' '), 0, 3, 3, 1, this, 0),
        Stick(mapOf(C to Done(), L to SelfInsert('\n')), 3, 3, 1, 1, this),
        )
    val lowerSticks: Collection<Stick> = listOf(
        Stick(mapOf(C to 'a', R to '-', BL to '$', BR to 'v'), 0, 0, 1, 1, this, 0),
        Stick(mapOf(C to 'n', TL to '`', T to '^', TR to '’', R to '!', BL to '/', B to 'l', BR to '\\'), 1, 0, 1, 1, this, 0),
        Stick(mapOf(C to 'i', L to '?', BL to 'x', B to '='), 2, 0, 1, 1, this, 0),

        Stick(mapOf(C to 'h', TL to '{', TR to '%', L to '(', R to 'k', BL to '[', BR to '_'), 0, 1, 1, 1, this, 0),
        Stick(mapOf(C to 'o', TL to 'q', T to 'u', TR to 'p', L to 'c', R to 'b', BL to 'g', B to 'd', BR to 'j'), 1, 1, 1, 1, this, 0),
        Stick(mapOf(C to SelfInsert('r'), TL to SelfInsert('|'), T to RaiseCase(), TR to SelfInsert('}'), L to SelfInsert('m'), R to SelfInsert(')'), BL to SelfInsert('@'), B to LowerCase(), BR to SelfInsert(']')), 2, 1, 1, 1, this),
        Stick(mapOf(C to Nums()), 3, 1, 1, 1, this),

        Stick(mapOf(C to 't', TL to '~', TR to 'y', L to '<', R to '*'), 0, 2, 1, 1, this, 0),
        Stick(mapOf(C to 'e', TL  to '"', T to 'w', TR to '\'', R to 'z', BL to ',', B to '.'), 1, 2, 1, 1, this, 0),
        Stick(mapOf(C to 's', TL to 'f', T to '&', TR to '°', L to '&', R to '>'), 2, 2, 1, 1, this, 0),
        Stick(mapOf(C to Backspace()), 3, 2, 1, 1, this),

        Stick(mapOf(C to ' '), 0, 3, 3, 1, this, 0),
        Stick(mapOf(C to Done(), L to SelfInsert('\n')), 3, 3, 1, 1, this),
        )
    val numSticks : Collection<Stick> = listOf(
        Stick(mapOf(C to '1'), 0, 0, 1, 1, this, 0),
        Stick(mapOf(C to '2', R to '!', B to 'l'), 1, 0, 1, 1, this, 0),
        Stick(mapOf(C to SelfInsert('3'), L to SelfInsert('?')), 2, 0, 1, 1, this),

        Stick(mapOf(C to '4', R to 'k'), 0, 1, 1, 1, this, 0),
        Stick(mapOf(C to '5', TL to 'A', T to 'B', TR to 'C', L to 'D', R to 'E', BL to 'F', B to 'x'), 1, 1, 1, 1, this, 0),
        Stick(mapOf(C to SelfInsert('6')), 2, 1, 1, 1, this),
        Stick(mapOf(C to Backspace()), 3, 2, 1, 1, this),

        Stick(mapOf(C to '7'), 0, 2, 1, 1, this, 0),
        Stick(mapOf(C to '8', BL to ',', B to '.'), 1, 2, 1, 1, this, 0),
        Stick(mapOf(C to '9'), 2, 2, 1, 1, this, 0),
        Stick(mapOf(C to Alpha()), 3, 1, 1, 1, this),

        Stick(mapOf(C to '0'), 0, 3, 2, 1, this, 0),
        Stick(mapOf(C to ' '), 2, 3, 3, 1, this, 0),
        Stick(mapOf(C to Done(), L to SelfInsert('\n')), 3, 3, 1, 1, this),

        )
    val sticks : Map<StickboardState, Collection<Stick>> = mapOf(StickboardState.LOWER to lowerSticks,
        StickboardState.UPPER to upperSticks,
        StickboardState.UPPERLOCK to upperSticks,
        StickboardState.NUMS to numSticks)
    private var currentState = StickboardState.UPPER
    fun getCurrentState() : StickboardState {
        return currentState
    }
    fun setCurrentState(newState : StickboardState) {
        parent.invalidate()
        currentState = newState
    }
    val width: Int = upperSticks.maxOf { s -> s.l + s.w }
    val height: Int = upperSticks.maxOf { s -> s.t + s.h }
    var padding: Int = 10
    var stickScale: Int = 0;
    var xOff: Int = 0
    var yOff: Int = 0

    val fPaint = Paint().apply {
        color = 0xff111111u.toInt()
        style = Paint.Style.FILL
    }
    val sPaint = Paint().apply {
        color = 0xff888888u.toInt()
        strokeWidth = 5.0f
        style = Paint.Style.STROKE
    }
    val pPaint = Paint().apply {
        color = 0xffa0a0a0u.toInt()
        style = Paint.Style.FILL
        textSize = 40f
    }
    val nPaint = Paint().apply {
        color = 0xff888888u.toInt()
        style = Paint.Style.FILL
        textSize = 25f
    }
    fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        val hScale = w / width
        val vScale = h / height
        stickScale = min(hScale, vScale)
        xOff = (w - (stickScale * width)) / 2
        yOff = (h - (stickScale * height)) / 2
    }

    fun onRender(canvas: Canvas) {
        for (stick in sticks.getOrDefault(currentState, listOf())) {
            stick.onRender(canvas)
        }
    }

    var startX: Float? = null
    var startY: Float? = null
    var startStick: Stick? = null
    fun onTouchEvent(me: MotionEvent): Boolean {
        var handled = false
        if (me.action == MotionEvent.ACTION_UP || me.action == MotionEvent.ACTION_DOWN) {
            val x = me.x - xOff
            val y = me.y - yOff
//            Log.i("Kbd", "adjusted pos is: ($x, $y)")
            for (stick in sticks.getOrDefault(currentState, listOf())) {
                // TODO: this could probably use some fuzziness
                val l = stick.l * stickScale
                val r = (stick.l + stick.w) * stickScale
                val t = stick.t * stickScale
                val b = (stick.t + stick.h) * stickScale
//                Log.i("Kbd", "Looking at Sticck <${stick.actions[StickPos.C]}>")
//                Log.i("Kbd", "comparing X $l, $x, $r")
//                Log.i("Kbd", "comparing Y $t, $y, $b")
                if (x >= l && x <= r &&
                    y >= t && y <= b
                ) {
                    //val c = stick.chars[StickPos.C]Se
                    if (me.action == MotionEvent.ACTION_UP) {
                        val dx = getDiff(x, startX ?: 5f)
                        val dy = getDiff(y, startY ?: 5f)
                        val sPos = fromDelta(dx, dy) ?: continue
                        val action = startStick!!.actions[sPos] ?: continue
                        action.doAction(this)
                    } else {
                        Log.i("kbd", "down on ${stick.actions[StickPos.C]}")
                        startX = x
                        startY = y
                        startStick = stick
                    }
                    handled = true
                    break
                }

            }
        }
        return handled;
    }
    fun getDiff(init : Float, cur : Float): Int {
        val d = init - cur
        if (abs(d) < stickScale * 0.4)
            return 0;
        return sign(d).toInt()
    }

}