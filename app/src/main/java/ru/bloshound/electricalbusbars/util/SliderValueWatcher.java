package ru.bloshound.electricalbusbars.util;

import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;

import com.google.android.material.slider.Slider;


public class SliderValueWatcher implements TextWatcher {

    Slider slider;


    public SliderValueWatcher(Slider slider) {
        super();
        this.slider = slider;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        slider.setActivated(false);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        slider.setActivated(false);
    }

    @Override
    public void afterTextChanged(Editable s) {

        if (TextUtils.isDigitsOnly(s) && !TextUtils.isEmpty(s)) {
            int value = Integer.parseInt(s.toString());
            if (value >= slider.getValueFrom() && value <= slider.getValueTo())
                slider.setValue(value);

        }
        slider.setActivated(true);
    }
}
