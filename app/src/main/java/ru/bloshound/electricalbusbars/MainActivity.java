package ru.bloshound.electricalbusbars;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.slider.Slider;

import ru.bloshound.electricalbusbars.util.AfterChangeTextWatcher;
import ru.bloshound.electricalbusbars.util.MinMaxEditTextWatcher;

public class MainActivity extends AppCompatActivity {

    private SharedPreferencesHelper mSharedPreferencesHelper;

    private ArrayAdapter<String> mMaterialAdapter;
    private AutoCompleteTextView mMaterail_autotv;

    private EditText mQuantity_ed;
    private EditText mLength_ed;
    private EditText mWidth_ed;
    private EditText mThickness_ed;

    private Slider mQuantity_slider;
    private Slider mLength_slider;
    private Slider mWidth_slider;
    private Slider mThickness_slider;

    private View.OnFocusChangeListener mOnMaterialFocusChangeListener = (v, hasFocus) -> {
        if (hasFocus) mMaterail_autotv.showDropDown();
    };

    private MinMaxEditTextWatcher mQuantityMinMaxInput;
    private SliderAfterChangeTextWatcher mQuantityInputWatchSlider;
    private Slider.OnChangeListener mQuantitySliderListener;

    private MinMaxEditTextWatcher mLengthMinMaxInput;
    private SliderAfterChangeTextWatcher mLengthInputWatchSlider;
    private Slider.OnChangeListener mLengthSliderListener;

    private MinMaxEditTextWatcher mWidthMinMaxInput;
    private SliderAfterChangeTextWatcher mWidthInputWatchSlider;
    private Slider.OnChangeListener mWidthSliderListener;

    private MinMaxEditTextWatcher mThicknessMinMaxInput;
    private SliderAfterChangeTextWatcher mThicknessInputWatchSlider;
    private Slider.OnChangeListener mThicknessSliderListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        mSharedPreferencesHelper = new SharedPreferencesHelper(this);

        mMaterail_autotv = findViewById(R.id.actv_chosen_material);

        mQuantity_ed = findViewById(R.id.ed_quantity);
        mLength_ed = findViewById(R.id.ed_length);
        mWidth_ed = findViewById(R.id.ed_width);
        mThickness_ed = findViewById(R.id.ed_thickness);

        mQuantity_slider = findViewById(R.id.slider_quantity);
        mLength_slider = findViewById(R.id.slider_length);
        mWidth_slider = findViewById(R.id.slider_width);
        mThickness_slider = findViewById(R.id.slider_thickness);


        mMaterialAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line,
                mSharedPreferencesHelper.getMaterials());
        mMaterail_autotv.setAdapter(mMaterialAdapter);


        mQuantityMinMaxInput = new MinMaxEditTextWatcher(getResources().getInteger(R.integer.min_value),
                getResources().getInteger(R.integer.quantity_max_value));
        mLengthMinMaxInput = new MinMaxEditTextWatcher(getResources().getInteger(R.integer.min_value),
                getResources().getInteger(R.integer.length_max_value));
        mWidthMinMaxInput = new MinMaxEditTextWatcher(getResources().getInteger(R.integer.min_value),
                getResources().getInteger(R.integer.width_max_value));
        mThicknessMinMaxInput = new MinMaxEditTextWatcher(getResources().getInteger(R.integer.min_value),
                getResources().getInteger(R.integer.thickness_max_value));


        mQuantityInputWatchSlider = new SliderAfterChangeTextWatcher(mQuantity_slider);
        mLengthInputWatchSlider = new SliderAfterChangeTextWatcher(mLength_slider);
        mWidthInputWatchSlider = new SliderAfterChangeTextWatcher(mWidth_slider);
        mThicknessInputWatchSlider = new SliderAfterChangeTextWatcher(mThickness_slider);


        mQuantitySliderListener = (slider, value, fromUser) -> mQuantity_ed.setText(String.valueOf((int) value));
        mLengthSliderListener = (slider, value, fromUser) -> mLength_ed.setText(String.valueOf((int) value));
        mWidthSliderListener = (slider, value, fromUser) -> mWidth_ed.setText(String.valueOf((int) value));
        mThicknessSliderListener = (slider, value, fromUser) -> mThickness_ed.setText(String.valueOf((int) value));

        setHints();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setWatchersAndListeners();
    }

    @Override
    protected void onPause() {

        String material;
        double density;
        int length, width, thickness;

        if (TextUtils.isEmpty(mMaterail_autotv.getText()))
            Toast.makeText(this, "material not chosen", Toast.LENGTH_SHORT).show();

        if (TextUtils.isEmpty(mLength_ed.getText()))
            Toast.makeText(this, "length not selected", Toast.LENGTH_SHORT).show();

        if (TextUtils.isEmpty(mWidth_ed.getText()))
            Toast.makeText(this, "width not selected", Toast.LENGTH_SHORT).show();

        if (TextUtils.isEmpty(mThickness_ed.getText()))
            Toast.makeText(this, "thickness not selected", Toast.LENGTH_SHORT).show();


        super.onPause();


    }


    private void setHints() {
        Resources r = getResources();
        mQuantity_ed.setHint(r.getInteger(R.integer.min_value) + " - " + r.getInteger(R.integer.quantity_max_value));
        mLength_ed.setHint(r.getInteger(R.integer.min_value) + " - " + r.getInteger(R.integer.length_max_value));
        mWidth_ed.setHint(r.getInteger(R.integer.min_value) + " - " + r.getInteger(R.integer.width_max_value));
        mThickness_ed.setHint(r.getInteger(R.integer.min_value) + " - " + r.getInteger(R.integer.thickness_max_value));
    }

    private void setWatchersAndListeners() {
        mMaterail_autotv.setOnFocusChangeListener(mOnMaterialFocusChangeListener);

        mQuantity_ed.addTextChangedListener(mQuantityMinMaxInput);
        mQuantity_ed.addTextChangedListener(mQuantityInputWatchSlider);
        mQuantity_slider.addOnChangeListener(mQuantitySliderListener);

        mLength_ed.addTextChangedListener(mLengthMinMaxInput);
        mLength_ed.addTextChangedListener(mLengthInputWatchSlider);
        mLength_slider.addOnChangeListener(mLengthSliderListener);

        mWidth_ed.addTextChangedListener(mWidthMinMaxInput);
        mWidth_ed.addTextChangedListener(mWidthInputWatchSlider);
        mWidth_slider.addOnChangeListener(mWidthSliderListener);

        mThickness_ed.addTextChangedListener(mThicknessMinMaxInput);
        mThickness_ed.addTextChangedListener(mThicknessInputWatchSlider);
        mThickness_slider.addOnChangeListener(mThicknessSliderListener);

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