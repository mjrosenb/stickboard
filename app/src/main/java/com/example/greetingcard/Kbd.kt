package com.example.greetingcard

import com.example.greetingcard.R
import android.R.id.keyboardView
import android.inputmethodservice.InputMethodService
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener
import android.util.Log
import android.view.View


class Kbd : InputMethodService(), OnKeyboardActionListener {
    val TAG = "Kbd";
    var keyboard : Keyboard? = null
    var caps : Boolean = false
    override fun onCreate() {
        Log.i(TAG,"onCreate for Kbd!");
        super.onCreate();
    }
    override fun onDestroy() {
        Log.i(TAG, "onDestroy called!");
        super.onDestroy();
    }
    override fun onInitializeInterface() {
        Log.i(TAG,"onInitializeInterface")
        super.onInitializeInterface();
    }
    override fun onCreateInputView() : View {
        Log.i(TAG, "onCreateInputView");
        val keyboardView = layoutInflater.inflate(R.layout.keyboard_view, null) as KeyboardView
        keyboard = Keyboard(this, R.xml.keys_layout)
        keyboardView.setKeyboard(keyboard)
        keyboardView.setOnKeyboardActionListener(this)
        return keyboardView
    }

    override fun setInputView(view: View?) {
        super.setInputView(view)
    }

    override fun onPress(primaryCode: Int) {
        TODO("Not yet implemented")
    }

    override fun onRelease(primaryCode: Int) {
        TODO("Not yet implemented")
    }

    override fun onKey(primaryCode: Int, keyCodes: IntArray?) {
        TODO("Not yet implemented")
    }

    override fun onText(text: CharSequence?) {
        TODO("Not yet implemented")
    }

    override fun swipeLeft() {
        TODO("Not yet implemented")
    }

    override fun swipeRight() {
        TODO("Not yet implemented")
    }

    override fun swipeDown() {
        TODO("Not yet implemented")
    }

    override fun swipeUp() {
        TODO("Not yet implemented")
    }

}