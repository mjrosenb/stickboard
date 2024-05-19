package net.rosenbridge.stickboard

import com.example.stickboard.R
import android.inputmethodservice.InputMethodService
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener
import android.text.TextUtils
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputConnection


class Kbd : InputMethodService(), OnKeyboardActionListener, Stickboard.OnStickEventListener {
    val TAG = "Kbd";
    var keyboard: Keyboard? = null
    var caps: Boolean = false
    override fun onCreate() {
        Log.i(TAG, "onCreate for Kbd!");
        super.onCreate();
    }

    override fun onDestroy() {
        Log.i(TAG, "onDestroy called!");
        super.onDestroy();
    }

    override fun onInitializeInterface() {
        Log.i(TAG, "onInitializeInterface")
        super.onInitializeInterface();
    }

    override fun onCreateInputView(): View {
        Log.i(TAG, "onCreateInputView");
        val keyboardView = layoutInflater.inflate(R.layout.kbdview, null) as KbdView
        keyboardView.setCallback(this)
        return keyboardView
    }

    fun onCreateInputViewFake(): View {
        val keyboardView = layoutInflater.inflate(R.layout.keyboard_view_wrap, null) as KeyboardView
        keyboard = Keyboard(this, R.xml.keys_layout)
        keyboardView.setKeyboard(keyboard)
        keyboardView.setOnKeyboardActionListener(this)
        val layoutParams = keyboardView.layoutParams
        if (layoutParams != null) {
            val w = layoutParams.width
            val h = layoutParams.height
            Log.i(TAG, "w: $w, h: $h")
            Log.i(TAG, "layoutParams: $layoutParams")
        } else {
            Log.i(TAG, "layoutParams is null?!")
        }
        Log.i(TAG, "created keyboardView: $keyboardView")
        val h = keyboardView.height
        Log.i(TAG, "real height: $h")
        return keyboardView
    }

    override fun setInputView(view: View?) {
        Log.i(TAG, "setting input view to: $view")
        if (view != null) {
            val lp = view.layoutParams
            Log.i(TAG, "input view's layoutParams: ${lp}")
        }
        super.setInputView(view)
    }
    // random debug helpers
    override fun onPress(primaryCode: Int) {
        Log.e(TAG, "key pressed: $primaryCode")
    }

    override fun onRelease(primaryCode: Int) {
        Log.e(TAG, "key released: $primaryCode")
    }

    override fun onText(text: CharSequence?) {
        Log.e(TAG, "onText <<$text>>")
    }

    override fun swipeLeft() {
        Log.e(TAG, "swipe left")
    }

    override fun swipeRight() {
        Log.e(TAG, "Swipe right")
    }

    override fun swipeDown() {
        Log.e(TAG, "swipeDown")
    }

    override fun swipeUp() {
        Log.e(TAG, "swipeUp")
    }

    override fun onKey(primaryCode: Int, keyCodes: IntArray?) {
        Log.e(TAG, "onKey: $primaryCode")
        val inputConnection: InputConnection = currentInputConnection ?: return
        when (primaryCode) {
            Keyboard.KEYCODE_DELETE -> {
                val selectedText = inputConnection.getSelectedText(0);
                if (TextUtils.isEmpty(selectedText)) {
                    inputConnection.deleteSurroundingText(1, 0);
                } else {
                    inputConnection.commitText("", 1);
                }
            }

            Keyboard.KEYCODE_DONE -> inputConnection.sendKeyEvent(
                KeyEvent(
                    KeyEvent.ACTION_DOWN,
                    KeyEvent.KEYCODE_ENTER
                )
            );
            else -> {
                var code = primaryCode.toChar()
                if (Character.isLetter(code) && caps) {
                    code = code.uppercaseChar()
                }
                inputConnection.commitText(code.toString(), 1)
            }
        }
    }

    override fun onChar(code: Char) {
        val inputConnection: InputConnection = currentInputConnection ?: return
        inputConnection.commitText(code.toString(), 1)

    }

    override fun onDel() {
        val inputConnection: InputConnection = currentInputConnection ?: return

        val selectedText = inputConnection.getSelectedText(0);
        if (TextUtils.isEmpty(selectedText)) {
            inputConnection.deleteSurroundingText(1, 0);
        } else {
            inputConnection.commitText("", 1);
        }
    }
    override fun onDone() {
        val inputConnection: InputConnection = currentInputConnection ?: return
        inputConnection.sendKeyEvent(
            KeyEvent(
                KeyEvent.ACTION_DOWN,
                KeyEvent.KEYCODE_ENTER
            )
        )
    }


}