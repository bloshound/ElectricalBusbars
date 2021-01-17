package ru.bloshound.electricalbusbars.ui.fragment;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.slider.Slider;

import ru.bloshound.electricalbusbars.R;
import ru.bloshound.electricalbusbars.repo.Busbar;
import ru.bloshound.electricalbusbars.repo.CopperBusbar;
import ru.bloshound.electricalbusbars.util.AfterTextChangeWatcher;
import ru.bloshound.electricalbusbars.util.InputFilterMinMax;
import ru.bloshound.electricalbusbars.util.slider.SliderObserver;

/**
 * A placeholder fragment containing a simple view.
 */
public class BusbarHolderFragment extends Fragment {

    private int minValue = getResources().getInteger(R.integer.min_value);
    private int maxQuantityValue = getResources().getInteger(R.integer.quantity_max_value);
    private int maxLengthValue = getResources().getInteger(R.integer.length_max_value);
    private int maxThicknessValue = getResources().getInteger(R.integer.thickness_max_value);
    private int maxWidthValue = getResources().getInteger(R.integer.thickness_max_value);


    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_BUSBAR = "busbar";


    private BusbarViewModel busbarViewModel;

    public static BusbarHolderFragment newInstance(int index) {
        BusbarHolderFragment fragment = new BusbarHolderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        bundle.putSerializable(ARG_BUSBAR, new CopperBusbar(4, 40, 1000)); // ЗАменить на получениме busbur мз preferences
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        busbarViewModel = new ViewModelProvider(this).get(BusbarViewModel.class);
        int index = 1;
        Busbar busbar = null; // здесь должен создаваться новый басбар
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
            busbar = (Busbar) getArguments().getSerializable(ARG_BUSBAR);
        }
        busbarViewModel.setIndex(index);
        busbarViewModel.setBusbar(busbar);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main, container, false);

        MutableLiveData<String> quantityLiveData = (MutableLiveData<String>) busbarViewModel.getQuantity();
        MutableLiveData<String> lenthLiveData = (MutableLiveData<String>) busbarViewModel.getLength();
        MutableLiveData<String> thicknessLiveData = (MutableLiveData<String>)busbarViewModel.getThickness();
        MutableLiveData<String> widthLiveData = (MutableLiveData<String>)busbarViewModel.getWidth();

        final TextView textView = (TextView) root.findViewById(R.id.section_label);

        Slider quantitySlider = (Slider) root.findViewById(R.id.slider_quantity);
        Slider lengthSlider = (Slider) root.findViewById(R.id.slider_length);
        Slider widthSlider = (Slider) root.findViewById(R.id.slider_width);
        Slider thicknessSlider = (Slider) root.findViewById(R.id.slider_thickness);

        EditText quantityEditText = (EditText) root.findViewById(R.id.ed_quantity);
        EditText lengthEditText = (EditText) root.findViewById(R.id.ed_length);
        EditText widthEditText = (EditText) root.findViewById(R.id.ed_width);
        EditText thicknessEditText = (EditText) root.findViewById(R.id.ed_thickness);

        quantityEditText.setFilters(new InputFilter[]{new InputFilterMinMax(minValue, maxQuantityValue)});
        quantityLiveData.observe(this, new SliderObserver(quantitySlider));
        quantityEditText.addTextChangedListener(new AfterTextChangeWatcher(quantityLiveData));

        lengthEditText.setFilters(new InputFilter[]{new InputFilterMinMax(minValue, maxLengthValue)});
        lenthLiveData.observe(this, new SliderObserver(lengthSlider));
        lengthEditText.addTextChangedListener(new AfterTextChangeWatcher(quantityLiveData));

        thicknessEditText.setFilters(new InputFilter[]{new InputFilterMinMax(minValue, maxThicknessValue)});
        thicknessLiveData.observe(this, new SliderObserver(thicknessSlider));
        thicknessEditText.addTextChangedListener(new AfterTextChangeWatcher(thicknessLiveData));

        widthEditText.setFilters(new InputFilter[]{new InputFilterMinMax(minValue, maxWidthValue)});
        widthLiveData.observe(this, new SliderObserver(widthSlider));
        widthEditText.addTextChangedListener(new AfterTextChangeWatcher(widthLiveData));


        busbarViewModel.getText().observe(this, s -> textView.setText(s));
        return root;

    }
}