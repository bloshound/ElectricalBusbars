package ru.bloshound.electricalbusbars;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.slider.Slider;

public class MainActivity extends AppCompatActivity {


    EditText quantuty_ed;
    Slider quantity_slider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources resources = getResources();

        quantuty_ed = findViewById(R.id.ed_quantity);
        quantity_slider = findViewById(R.id.slider_quantity);

        quantuty_ed.addTextChangedListener(getMinMaxEditTextWatcher
                (resources.getInteger(R.integer.min_value),
                        resources.getInteger(R.integer.quantity_max_value)));


    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    private TextWatcher getMinMaxEditTextWatcher(int min, int max) {
        TextWatcher textWatcher = new TextWatcher() {

            CharSequence ch = "";


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





                }


            }
        };
        return textWatcher;
    }


    private TextWatcher getSliderSetValueTextWatcher(Slider slider) {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        };
        return textWatcher;
    }

}