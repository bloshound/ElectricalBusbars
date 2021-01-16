package ru.bloshound.electricalbusbars.util;

import android.text.InputFilter;
import android.text.Spanned;


public class InputFilterMinMax implements InputFilter {

    int min, max;

    public InputFilterMinMax(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public InputFilterMinMax(String min, String max) {
        this.min = Integer.parseInt(min);
        this.max = Integer.parseInt(max);
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            String prefix = dest.toString().substring(0, dstart);
            String insert = source.toString();
            String suffix = dest.toString().substring(dend);
            String input_string = prefix + insert + suffix;
            int input = Integer.parseInt(input_string);

            if (isInRange(min, max, input) || input_string.length() < String.valueOf(min).length())
                return null;

        } catch (NumberFormatException nfe) {
        }
        return "";
    }

    private boolean isInRange(int a, int b, int c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }

}
