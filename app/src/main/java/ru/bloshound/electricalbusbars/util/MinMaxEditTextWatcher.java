package ru.bloshound.electricalbusbars.util;

import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;

import androidx.annotation.IntegerRes;

public class MinMaxEditTextWatcher implements TextWatcher {

    private int min, max;

    public MinMaxEditTextWatcher(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (!TextUtils.isEmpty(s) && TextUtils.isDigitsOnly(s)) {
            int value = Integer.parseInt(s.toString());

            if (value > max) {
                s.replace(0, s.length(), String.valueOf(max));
            }
            if (value < min) {
                s.replace(0, s.length(), String.valueOf(min));
            }

            Selection.setSelection(s, s.length());
        }
    }
}
