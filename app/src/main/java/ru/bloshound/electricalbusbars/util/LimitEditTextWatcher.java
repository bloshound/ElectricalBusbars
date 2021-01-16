package ru.bloshound.electricalbusbars.util;

import android.text.Editable;
import android.widget.EditText;

public class LimitEditTextWatcher extends MinMaxTextWatcher {

    EditText ed;

    public LimitEditTextWatcher(int min, int max, EditText ed) {
        super(min, max);
        this.ed = ed;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String str = s.toString();
        int n = 0;
        try {
            n = Integer.parseInt(str);
            if (n < min) {
                ed.setText(min);
                //  Toast.makeText(getApplicationContext(), "Minimum allowed is " + min, Toast.LENGTH_SHORT).show();
            } else if (n > max) {
                ed.setText("" + max);
                //  Toast.makeText(getApplicationContext(), "Maximum allowed is " + max, Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException nfe) {
            ed.setText("" + min);
            //   Toast.makeText(getApplicationContext(), "Bad format for number!" + max, Toast.LENGTH_SHORT).show();
        }
    }
}

