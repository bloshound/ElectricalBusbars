package ru.bloshound.electricalbusbars.util.textedit;

import android.content.Context;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.KeyEvent;

import androidx.appcompat.widget.AppCompatEditText;

/**
 * @author skb-2
 * @date 22.01.2021
 */

public class EditTextCloseEvent extends AppCompatEditText {

    public EditTextCloseEvent(Context context) {
        super(context);
    }

    public EditTextCloseEvent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditTextCloseEvent(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            for (InputFilter filter : this.getFilters()) {
                if (filter instanceof InputFilterIntRange)
                    ((InputFilterIntRange) filter).onFocusChange(this, false);
            }
        }
        return super.dispatchKeyEvent(event);
    }
}