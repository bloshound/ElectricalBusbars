package ru.bloshound.electricalbusbars.util.slider;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import com.google.android.material.slider.Slider;

import androidx.annotation.NonNull;

public class SliderChangeWatcher implements TextWatcher {

    Slider slider;

    public SliderChangeWatcher(Slider slider) {
        this.slider = slider;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(@NonNull Editable s) {

        if (TextUtils.isDigitsOnly(s) && !TextUtils.isEmpty(s)) {
            int i = Integer.parseInt(s.toString());
            if (i >= slider.getValueFrom() && i <= slider.getValueTo()) slider.setValue(i);
        }


    }
}
