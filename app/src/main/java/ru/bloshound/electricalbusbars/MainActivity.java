package ru.bloshound.electricalbusbars;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.slider.Slider;

import ru.bloshound.electricalbusbars.util.AfterChangeTextWatcher;
import ru.bloshound.electricalbusbars.util.MinMaxEditTextWatcher;

public class MainActivity extends AppCompatActivity {

    EditText mQuantity_ed;
    Slider mQuantity_slider;



    MinMaxEditTextWatcher mQuantityMinMaxInput;
    SliderAfterChangeTextWatcher mQuantityInputWatcher;
    Slider.OnChangeListener mQuantitySliderListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        mQuantity_ed = findViewById(R.id.ed_quantity);
        mQuantity_slider = findViewById(R.id.slider_quantity);



        mQuantityMinMaxInput = new MinMaxEditTextWatcher(getResources().getInteger(R.integer.min_value),
                getResources().getInteger(R.integer.quantity_max_value));
        mQuantityInputWatcher = new SliderAfterChangeTextWatcher(mQuantity_slider);
        mQuantitySliderListener = (slider, value, fromUser) -> mQuantity_ed.setText(String.valueOf((int) value));

    }

    @Override
    protected void onResume() {
        super.onResume();

        mQuantity_ed.addTextChangedListener(mQuantityMinMaxInput);
        mQuantity_ed.addTextChangedListener(mQuantityInputWatcher);
        mQuantity_slider.addOnChangeListener(mQuantitySliderListener);

    }

    private static class SliderAfterChangeTextWatcher extends AfterChangeTextWatcher {
        Slider slider;

        public SliderAfterChangeTextWatcher(Slider slider) {
            this.slider = slider;
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!TextUtils.isEmpty(s) && TextUtils.isDigitsOnly(s)) {
                int value = Integer.parseInt(s.toString());
                slider.setValue(value);
            }
        }
    }
}