package ru.bloshound.electricalbusbars.ui.fragment;

import android.content.Context;
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
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.slider.Slider;

import ru.bloshound.electricalbusbars.R;
import ru.bloshound.electricalbusbars.repo.Busbar;
import ru.bloshound.electricalbusbars.repo.CopperBusbar;
import ru.bloshound.electricalbusbars.util.InputFilterMinMax;
import ru.bloshound.electricalbusbars.util.slider.SliderChangeWatcher;

/**
 * A placeholder fragment containing a simple view.
 */
public class BusbarHolderFragment extends Fragment {

    private int minValue;
    private int maxQuantityValue;
    private int maxLengthValue;
    private int maxThicknessValue;
    private int maxWidthValue;


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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        minValue = getResources().getInteger(R.integer.min_value);
        maxQuantityValue = getResources().getInteger(R.integer.quantity_max_value);
        maxLengthValue = getResources().getInteger(R.integer.length_max_value);
        maxThicknessValue = getResources().getInteger(R.integer.thickness_max_value);
        maxWidthValue = getResources().getInteger(R.integer.thickness_max_value);
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

        MutableLiveData<String> quantityLiveData = new MutableLiveData<>();
        quantityLiveData.setValue("5");
        MutableLiveData<String> lengthLiveData = new MutableLiveData<>();
        lengthLiveData.setValue("10");
        MutableLiveData<String> widthLiveData = new MutableLiveData<>();
        widthLiveData.setValue("15");
        MutableLiveData<String> thicknessLiveData = new MutableLiveData<>();
        thicknessLiveData.setValue("4");

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
       quantityEditText.addTextChangedListener(new SliderChangeWatcher(quantitySlider));

       lengthEditText.setFilters(new InputFilter[]{new InputFilterMinMax(minValue, maxLengthValue)});
       lengthEditText.addTextChangedListener(new SliderChangeWatcher(lengthSlider));




        busbarViewModel.getText().observe(this, s -> textView.setText(s));
        return root;

    }
}