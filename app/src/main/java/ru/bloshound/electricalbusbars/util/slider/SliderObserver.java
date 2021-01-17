package ru.bloshound.electricalbusbars.util.slider;

import android.text.TextUtils;

import androidx.lifecycle.Observer;

import com.google.android.material.slider.Slider;

public class SliderObserver implements Observer<String> {

    Slider slider;

    public SliderObserver(Slider slider){
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

