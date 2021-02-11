package ru.bloshound.electricalbusbars;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.slider.Slider;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import ru.bloshound.electricalbusbars.util.AfterChangeTextWatcher;
import ru.bloshound.electricalbusbars.util.MinMaxEditTextWatcher;

public class MainActivity extends AppCompatActivity {

    private static final String BACKGROUND_KEY = "background";
    private static final String DENSITY_FOCUSABLE_KEY = "density_focusable";
    private static final String DENSITY_FOCUSABLE_TOUCH_KEY = "density_focusable_touch";


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
    private TextView mGeometryInfo_tv;
    private ExtendedFloatingActionButton mSavaCalculate;


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

    private View.OnClickListener mSaveCalculateListener;


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
        mGeometryInfo_tv = findViewById(R.id.tv_geometry_information);

        mSavaCalculate = findViewById(R.id.fab_save_calculate);

        mMaterialAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line,
                mSharedPreferencesHelper.getMaterials());
        mMaterial_auto_tv.setAdapter(mMaterialAdapter);


        initFromPreferences(savedInstanceState);


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
                    root.setTag(R.drawable.gradient_copper);
                } else if (s.toString().toLowerCase().contains(r.getString(R.string.aluminium_material))) {
                    root.setBackground(ResourcesCompat.getDrawable(r, R.drawable.gradient_aluminium, null));
                    root.setTag(R.drawable.gradient_aluminium);
                } else {
                    root.setBackground(ResourcesCompat.getDrawable(r, R.drawable.gradient_other, null));
                    root.setTag(R.drawable.gradient_other);
                }
            }
        };


        mQuantityInputWatchSlider = new SliderAfterChangeTextWatcher(mQuantity_slider);
        mLengthInputWatchSlider = new SliderAfterChangeTextWatcher(mLength_slider);
        mWidthInputWatchSlider = new SliderAfterChangeTextWatcher(mWidth_slider);
        mThicknessInputWatchSlider = new SliderAfterChangeTextWatcher(mThickness_slider);


        mQuantitySliderListener = (slider, value, fromUser) -> {
            mQuantity_ed.setText(String.valueOf((int) value));
            setGeometryInfo();
        };
        mLengthSliderListener = (slider, value, fromUser) -> {
            mLength_ed.setText(String.valueOf((int) value));
            setGeometryInfo();
        };
        mWidthSliderListener = (slider, value, fromUser) -> {
            mWidth_ed.setText(String.valueOf((int) value));
            setGeometryInfo();
        };
        mThicknessSliderListener = (slider, value, fromUser) -> {
            mThickness_ed.setText(String.valueOf((int) value));
            setGeometryInfo();
        };


        mSaveCalculateListener = v -> {


        };

        mDensityCheckListener = (buttonView, isChecked) -> {
            Resources r = getResources();
            String material = mMaterial_auto_tv.getText().toString();
            String density;

            if (isChecked) {
                if (TextUtils.isEmpty(mDensity_ed.getText()))
                    mDensity_ed.setText(String.valueOf(r.getInteger(R.integer.min_value)));
                density = mDensity_ed.getText().toString();

                if (material.toLowerCase().contains(r.getString(R.string.copper_material))) {
                    mDensity_ed.setFocusableInTouchMode(false);
                    mDensity_ed.setFocusable(false);
                    if (Integer.parseInt(density) != r.getInteger(R.integer.copper_density)) {
                        mDensity_ed.setText(String.valueOf(r.getInteger(R.integer.copper_density)));
                    }

                }
                if (material.toLowerCase().contains(r.getString(R.string.aluminium_material))) {
                    mDensity_ed.setFocusableInTouchMode(false);
                    mDensity_ed.setFocusable(false);
                    if (Integer.parseInt(density) != r.getInteger(R.integer.aluminium_density)) {
                        mDensity_ed.setText(String.valueOf(r.getInteger(R.integer.aluminium_density)));
                    }
                }

            } else {
                mDensity_ed.setFocusableInTouchMode(true);
                mDensity_ed.setFocusable(true);
            }
        };


        setHints();
    }


    @Override
    protected void onResume() {
        super.onResume();
        setWatchersAndListeners();
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(BACKGROUND_KEY, (int) root.getTag());

        outState.putBoolean(DENSITY_FOCUSABLE_KEY, mDensity_ed.isFocusable());
        outState.putBoolean(DENSITY_FOCUSABLE_TOUCH_KEY, mDensity_ed.isFocusableInTouchMode());

    }


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), savedInstanceState.getInt(BACKGROUND_KEY), null);
        root.setBackground(drawable);
        root.setTag(savedInstanceState.getInt(BACKGROUND_KEY));

        mDensity_ed.setFocusable(savedInstanceState.getBoolean(DENSITY_FOCUSABLE_KEY));
        mDensity_ed.setFocusableInTouchMode(savedInstanceState.getBoolean(DENSITY_FOCUSABLE_TOUCH_KEY));

    }

    private void initFromPreferences(Bundle savedInstanceState) {
        Resources r = getResources();
        String initMaterial;
        int initQuantity, initDensity, initLength, initWidth, initThickness;
        if (mSharedPreferencesHelper.getLastMaterial() == null) {

            initMaterial = new Random().nextBoolean() ?
                    r.getString(R.string.aluminium_material) : r.getString(R.string.copper_material);

            initDensity = initMaterial.equalsIgnoreCase(r.getString(R.string.copper_material)) ?
                    r.getInteger(R.integer.copper_density) : r.getInteger(R.integer.aluminium_density);

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

        if (savedInstanceState == null) {

            if (initMaterial.toLowerCase().contains(r.getString(R.string.copper_material))) {
                root.setBackground(ResourcesCompat.getDrawable(r, R.drawable.gradient_copper, null));
                root.setTag(R.drawable.gradient_copper);
            } else if (initMaterial.toLowerCase().contains(r.getString(R.string.aluminium_material))) {
                root.setBackground(ResourcesCompat.getDrawable(r, R.drawable.gradient_aluminium, null));
                root.setTag(R.drawable.gradient_aluminium);
            } else {
                root.setBackground(ResourcesCompat.getDrawable(r, R.drawable.gradient_other, null));
                root.setTag(R.drawable.gradient_other);
            }
        }

        setGeometryInfo();
    }


    private void setHints() {
        Resources r = getResources();
        mQuantity_ed.setHint(r.getInteger(R.integer.min_value) + " - " + r.getInteger(R.integer.quantity_max_value));
        mLength_ed.setHint(r.getInteger(R.integer.min_value) + " - " + r.getInteger(R.integer.length_max_value));
        mWidth_ed.setHint(r.getInteger(R.integer.min_value) + " - " + r.getInteger(R.integer.width_max_value));
        mThickness_ed.setHint(r.getInteger(R.integer.min_value) + " - " + r.getInteger(R.integer.thickness_max_value));
        mDensity_ed.setHint(r.getInteger(R.integer.min_value) + " - " + r.getInteger(R.integer.density_max_value));
    }

    private void setGeometryInfo() {
        List<EditText> viewList = Arrays.asList(mQuantity_ed, mLength_ed, mWidth_ed, mThickness_ed);
        for (EditText et : viewList) {
            if (TextUtils.isEmpty(et.getText()) && !TextUtils.isDigitsOnly(et.getText())) {
                return;
            }
        }
        int squareOfSection = Integer.parseInt(mWidth_ed.getText().toString()) * Integer.parseInt(mThickness_ed.getText().toString());
        int capacity1 = squareOfSection * Integer.parseInt(mLength_ed.getText().toString());
        int capacity_all = capacity1 * Integer.parseInt(mQuantity_ed.getText().toString());

        StringBuilder geometryInfoSb = new StringBuilder();
        geometryInfoSb.append("Square of section is ").append(squareOfSection).append(" m\u00B2.").append("\n");
        geometryInfoSb.append("Capacity of one busbar is ").append(capacity1).append(" m\u00B3.").append("\n");
        geometryInfoSb.append("Capacity of quantity busbar is ").append(capacity_all).append(" m\u00B3.").append("\n");

        mGeometryInfo_tv.setText(geometryInfoSb);
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
                    if (TextUtils.isEmpty(((EditText) v).getText())) {
                        ((EditText) v).setText(String.valueOf((context.getResources().getInteger(R.integer.min_value))));

                        Toast toast = Toast.makeText(context,
                                "Parametr: " + (String) v.getTag() + " is empty,\nseted minimal available value",
                                Toast.LENGTH_SHORT);
                        View parentV_x2 = (View) v.getParent().getParent();
                        toast.setGravity(Gravity.TOP, 0, (int) parentV_x2.getY() + 200);
                        toast.show();
                    }
                }
            }
        }
    }
}