package ru.bloshound.electricalbusbars;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.google.android.material.slider.Slider;

import java.util.Random;

import ru.bloshound.electricalbusbars.util.AfterChangeTextWatcher;
import ru.bloshound.electricalbusbars.util.MinMaxEditTextWatcher;

public class MainActivity extends AppCompatActivity {

    private SharedPreferencesHelper mSharedPreferencesHelper;
    private ArrayAdapter<String> mMaterialAdapter;

    private View root;

    private CheckBox mDensity_check;
    private EditText mDensity_ed;
    private AutoCompleteTextView mMaterial_auto_tv;
    private EditText mQuantity_ed;
    private EditText mLength_ed;
    private EditText mWidth_ed;
    private EditText mThickness_ed;

    private Slider mQuantity_slider;
    private Slider mLength_slider;
    private Slider mWidth_slider;
    private Slider mThickness_slider;

    private View.OnFocusChangeListener mOnMaterialFocusChangeListener = (v, hasFocus) -> {
        if (hasFocus) mMaterial_auto_tv.showDropDown();
    };

    private AfterChangeTextWatcher mMaterialWatcher;


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


    private MinMaxEditTextWatcher mDensityMinMaxInput;

    private CompoundButton.OnCheckedChangeListener mDensityCheckListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        mSharedPreferencesHelper = new SharedPreferencesHelper(this);

        root = findViewById(R.id.rl_root_view);

        mDensity_ed = findViewById(R.id.ed_density);
        mDensity_check = findViewById(R.id.check_density);
        mMaterial_auto_tv = findViewById(R.id.auto_tv_chosen_material);

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
        mMaterial_auto_tv.setAdapter(mMaterialAdapter);

        initFromPreferences();


        mQuantityMinMaxInput = new MinMaxEditTextWatcher(getResources().getInteger(R.integer.min_value),
                getResources().getInteger(R.integer.quantity_max_value));
        mLengthMinMaxInput = new MinMaxEditTextWatcher(getResources().getInteger(R.integer.min_value),
                getResources().getInteger(R.integer.length_max_value));
        mWidthMinMaxInput = new MinMaxEditTextWatcher(getResources().getInteger(R.integer.min_value),
                getResources().getInteger(R.integer.width_max_value));
        mThicknessMinMaxInput = new MinMaxEditTextWatcher(getResources().getInteger(R.integer.min_value),
                getResources().getInteger(R.integer.thickness_max_value));
        mDensityMinMaxInput = new MinMaxEditTextWatcher(getResources().getInteger(R.integer.min_value),
                getResources().getInteger(R.integer.density_max_value));

        mMaterialWatcher = new AfterChangeTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                Resources r = getResources();
                if (s.toString().toLowerCase().contains(r.getString(R.string.copper_material))) {
                    root.setBackground(ResourcesCompat.getDrawable(r, R.drawable.gradient_copper, null));
                } else if (s.toString().toLowerCase().contains(r.getString(R.string.aluminium_material))) {
                    root.setBackground(ResourcesCompat.getDrawable(r, R.drawable.gradient_aluminium, null));
                } else
                    root.setBackground(ResourcesCompat.getDrawable(r, R.drawable.gradient_other, null));
            }
        };


        mQuantityInputWatchSlider = new SliderAfterChangeTextWatcher(mQuantity_slider);
        mLengthInputWatchSlider = new SliderAfterChangeTextWatcher(mLength_slider);
        mWidthInputWatchSlider = new SliderAfterChangeTextWatcher(mWidth_slider);
        mThicknessInputWatchSlider = new SliderAfterChangeTextWatcher(mThickness_slider);


        mQuantitySliderListener = (slider, value, fromUser) -> mQuantity_ed.setText(String.valueOf((int) value));
        mLengthSliderListener = (slider, value, fromUser) -> mLength_ed.setText(String.valueOf((int) value));
        mWidthSliderListener = (slider, value, fromUser) -> mWidth_ed.setText(String.valueOf((int) value));
        mThicknessSliderListener = (slider, value, fromUser) -> mThickness_ed.setText(String.valueOf((int) value));

        mDensityCheckListener = (buttonView, isChecked) -> {


            Resources r = getResources();
            String density = mDensity_ed.getText().toString();
            String materail = mMaterial_auto_tv.getText().toString();

            if (isChecked) {
                mDensity_ed.clearFocus();
                if (!TextUtils.isEmpty(density)) {
                    if (materail.toLowerCase().contains(r.getString(R.string.copper_material))) {
                        if (Integer.parseInt(density) != r.getInteger(R.integer.copper_density)) {
                            mDensity_ed.setText(String.valueOf(r.getInteger(R.integer.copper_density)));
                        }

                    }
                }
            }
        };

        setHints();


    }


    @Override
    protected void onResume() {
        super.onResume();
        setWatchersAndListeners();
    }

    private void initFromPreferences() {
        Resources r = getResources();
        String initMaterial;
        int initQuantity, initDensity, initLength, initWidth, initThickness;
        if (TextUtils.isEmpty(mSharedPreferencesHelper.getLastMaterial())) {

            initMaterial = new Random().nextBoolean() ?
                    r.getString(R.string.aluminium_material) : r.getString(R.string.copper_material);

            initDensity =  initMaterial.equalsIgnoreCase(r.getString(R.string.copper_material))?
                    r.getInteger(R.integer.copper_density):r.getInteger(R.integer.aluminium_density);

            initQuantity = r.getInteger(R.integer.default_quantity);
            initLength = r.getInteger(R.integer.default_length);
            initWidth = r.getInteger(R.integer.default_width);
            initThickness = r.getInteger(R.integer.default_thickness);

        } else {
            initQuantity = mSharedPreferencesHelper.getLastQuantity();
            String material = mSharedPreferencesHelper.getLastMaterial();
            Busbar lastBusbar = mSharedPreferencesHelper.getSavedBusbars().get(material);
            initMaterial = lastBusbar.getMaterial();
            initDensity = lastBusbar.getDensity();
            initLength = lastBusbar.getLength();
            initWidth = lastBusbar.getWidth();
            initThickness = lastBusbar.getThickness();
        }

        mMaterial_auto_tv.setText(initMaterial);

        mDensity_ed.setText(String.valueOf(initDensity));

        mQuantity_ed.setText(String.valueOf(initQuantity));
        mQuantity_slider.setValue(initQuantity);

        mLength_ed.setText(String.valueOf(initLength));
        mLength_slider.setValue(initLength);

        mWidth_ed.setText(String.valueOf(initWidth));
        mWidth_slider.setValue(initWidth);

        mThickness_ed.setText(String.valueOf(initThickness));
        mThickness_slider.setValue(initThickness);

        if (initMaterial.toLowerCase().contains(r.getString(R.string.copper_material))) {
            root.setBackground(ResourcesCompat.getDrawable(r, R.drawable.gradient_copper, null));
        } else if (initMaterial.toLowerCase().contains(r.getString(R.string.aluminium_material))) {
            root.setBackground(ResourcesCompat.getDrawable(r, R.drawable.gradient_aluminium, null));
        } else root.setBackground(ResourcesCompat.getDrawable(r, R.drawable.gradient_other, null));
    }


    private void setHints() {
        Resources r = getResources();
        mQuantity_ed.setHint(r.getInteger(R.integer.min_value) + " - " + r.getInteger(R.integer.quantity_max_value));
        mLength_ed.setHint(r.getInteger(R.integer.min_value) + " - " + r.getInteger(R.integer.length_max_value));
        mWidth_ed.setHint(r.getInteger(R.integer.min_value) + " - " + r.getInteger(R.integer.width_max_value));
        mThickness_ed.setHint(r.getInteger(R.integer.min_value) + " - " + r.getInteger(R.integer.thickness_max_value));
        mDensity_ed.setHint(r.getInteger(R.integer.min_value)+ " - " + r.getInteger(R.integer.density_max_value));
    }

    private void setWatchersAndListeners() {
        mMaterial_auto_tv.setOnFocusChangeListener(mOnMaterialFocusChangeListener);
        mMaterial_auto_tv.addTextChangedListener(mMaterialWatcher);

        mQuantity_ed.addTextChangedListener(mQuantityMinMaxInput);
        mQuantity_ed.addTextChangedListener(mQuantityInputWatchSlider);
        mQuantity_ed.setOnFocusChangeListener(new EmptyToMinOnFocusChangeListener(this));
        mQuantity_slider.addOnChangeListener(mQuantitySliderListener);

        mLength_ed.addTextChangedListener(mLengthMinMaxInput);
        mLength_ed.addTextChangedListener(mLengthInputWatchSlider);
        mLength_ed.setOnFocusChangeListener(new EmptyToMinOnFocusChangeListener(this));
        mLength_slider.addOnChangeListener(mLengthSliderListener);

        mWidth_ed.addTextChangedListener(mWidthMinMaxInput);
        mWidth_ed.addTextChangedListener(mWidthInputWatchSlider);
        mWidth_ed.setOnFocusChangeListener(new EmptyToMinOnFocusChangeListener(this));
        mWidth_slider.addOnChangeListener(mWidthSliderListener);

        mThickness_ed.addTextChangedListener(mThicknessMinMaxInput);
        mThickness_ed.addTextChangedListener(mThicknessInputWatchSlider);
        mThickness_ed.setOnFocusChangeListener(new EmptyToMinOnFocusChangeListener(this));
        mThickness_slider.addOnChangeListener(mThicknessSliderListener);

        mDensity_ed.addTextChangedListener(mDensityMinMaxInput);
        mDensity_ed.setOnFocusChangeListener(new EmptyToMinOnFocusChangeListener(this));

        mDensity_check.setOnCheckedChangeListener(mDensityCheckListener);

    }

    //TextWatcher следящие за соотвествующеми Slider
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

    private static class EmptyToMinOnFocusChangeListener implements View.OnFocusChangeListener {

        Context context;

        public EmptyToMinOnFocusChangeListener(Context context) {
            this.context = context;
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus) {
                if (v instanceof EditText) {
                    if (TextUtils.isEmpty(((EditText) v).getText())) ((EditText) v)
                            .setText(String.valueOf((context.getResources().getInteger(R.integer.min_value))));
                }
            }
        }
    }
}