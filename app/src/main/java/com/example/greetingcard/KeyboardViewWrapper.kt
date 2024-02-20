package com.example.greetingcard

import android.content.Context
import android.graphics.Canvas
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.util.AttributeSet
import android.util.Log

class KeyboardViewWrapper(ctx : Context, attrs : AttributeSet) : KeyboardView(ctx, attrs) {
    val TAG = "Kbd"
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Log.i(TAG, "called onDraw!")
        Log.i(TAG, "height: $height")
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.i(TAG, "called onAttachedToWindow!")
        Log.i(TAG, "height: $height")
    }

    override fun setKeyboard(keyboard: Keyboard?) {
        Log.i(TAG, "called setKeyboard!")
        Log.i(TAG, "height: $height")
        super.setKeyboard(keyboard)
        Log.i(TAG, "height: $height")
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        Log.i(TAG, "onSizeChanged($w, $h, $oldw, $oldh)")
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Log.i(TAG, "called onMeasure($widthMeasureSpec, $heightMeasureSpec)")
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

}