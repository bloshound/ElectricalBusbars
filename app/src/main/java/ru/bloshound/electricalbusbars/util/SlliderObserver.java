package ru.bloshound.electricalbusbars.util;

import android.text.TextUtils;

import androidx.lifecycle.Observer;

import com.google.android.material.slider.Slider;

public class SlliderObserver implements Observer<String> {

    Slider slider;

    public SlliderObserver(Slider slider){
        this.slider = slider;
    }

    @Override
    public void onChanged(String s) {
        if (TextUtils.isDigitsOnly(s) && !TextUtils.isEmpty(s)) {
            int i = Integer.parseInt(s);
            if (i >= slider.getValueFrom() && i <= slider.getValueTo())
                slider.setValue(i);
        }
    }
}

