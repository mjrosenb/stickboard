package net.rosenbridge.stickboard

import android.content.Context
import android.content.res.Resources
import android.graphics.BlendMode
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.PopupWindow

class KbdView(ctx: Context, attrs : AttributeSet) : View(ctx, attrs, Resources.getSystem().getIdentifier("keyboardViewStyle", "attr", "android"), 0) {
    val mPopupKeyboard = PopupWindow(context)

    init {
        val res = Resources.getSystem().getIdentifier("keyboardViewStyle", "attr", "android")
        Log.i("Kbd", "Found resource $res")
        mPopupKeyboard.setBackgroundDrawable(null)
        setWillNotDraw(false)
    }
    private val stickBoard : Stickboard = Stickboard(this)
    private var mCallback : Stickboard.OnStickEventListener = Stickboard.EmptyListener()
    fun setCallback(cb : Stickboard.OnStickEventListener) {
        mCallback = cb
        stickBoard.setCallback(cb)
    }
//    val fPaint = Paint()
//
//    init {
//        fPaint.color = 0xff111111u.toInt()
//        fPaint.style = Paint.Style.FILL
//        // Log.i("Kbd", "blend mode: ${fPaint.blendMode}")
//    }
//    val sPaint = Paint()
//    init {
//        sPaint.color = 0xff888888u.toInt()
//        sPaint.strokeWidth = 5.0f
//        sPaint.style = Paint.Style.STROKE
//    }
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.i("Kbd", "KbdView@onAttachedToWindow")
    }
    override fun onDraw(canvas: Canvas) {
        Log.i("Kbd", "KbdView#onDraw")
        Log.i("Kbd", "width=$width, height=$height")

        super.onDraw(canvas)
        canvas.drawColor(0, BlendMode.COLOR)
//        canvas.drawColor(0xff800000, BlendMode.SRC_OVER)
//        canvas.drawColor(0xff400000, BlendMode.SRC)
//
//        canvas.drawColor(0xff000080, BlendMode.DST_OVER)
        canvas.drawColor(0xff800080)
        stickBoard.onRender(canvas)
//        val btnH = (height / 4).toFloat()
//        val pad = 10
//        for (x in 0..3) {
//            for (y in 0..3) {
//                val l = x * btnH
//                val t = y * btnH
//                val r = (x+1) * btnH - pad
//                var b = (y+1) * btnH - pad
//                Log.i("Kbd", "rect($l, $t, $r, $b)")
//                canvas.drawRect(l, t, r, b, fPaint)
//                canvas.drawRect(l, t, r, b, sPaint)
//
//            }
//        }
        // canvas.save()

    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        Log.i("Kbd", "KbdView#onSizeChanged($w,$h, $oldw, $oldh)")
        super.onSizeChanged(w, h, oldw, oldh)
        stickBoard.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // TODO: actually compute these using whatever android stuff is necessary.
        setMeasuredDimension(1080, 837)
    }
    override fun onTouchEvent(me : MotionEvent) : Boolean {
//        Log.i("Kbd", "handling onTouchEvent: $me")
        return stickBoard.onTouchEvent(me)
    }
}