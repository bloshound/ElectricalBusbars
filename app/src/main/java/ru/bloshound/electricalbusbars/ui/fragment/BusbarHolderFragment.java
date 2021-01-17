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
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.slider.Slider;

import ru.bloshound.electricalbusbars.R;
import ru.bloshound.electricalbusbars.util.InputFilterMinMax;
import ru.bloshound.electricalbusbars.util.VariableValueWatcher;

/**
 * A placeholder fragment containing a simple view.
 */
public class BusbarHolderFragment extends Fragment {


    private static final String ARG_SECTION_NUMBER = "section_number";



    private BusbarViewModel busbarViewModel;

    public static BusbarHolderFragment newInstance(int index) {
        BusbarHolderFragment fragment = new BusbarHolderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        busbarViewModel = new ViewModelProvider(this).get(BusbarViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        busbarViewModel.setIndex(index);




    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main, container, false);

        final TextView textView = (TextView) root.findViewById(R.id.section_label);

        Slider quantitySlider = (Slider) root.findViewById(R.id.slider_quantity);
        Slider lengthSlider = (Slider) root.findViewById(R.id.slider_length);
        Slider widthSlider = (Slider) root.findViewById(R.id.slider_width);
        Slider thicknessSlider = (Slider) root.findViewById(R.id.slider_thickness);

        EditText quantityEd = (EditText) root.findViewById(R.id.ed_quantity);
        EditText lengthEd = (EditText) root.findViewById(R.id.ed_length);
        EditText widthEd = (EditText) root.findViewById(R.id.ed_width);
        EditText thicknessEd = (EditText) root.findViewById(R.id.ed_thickness);


        quantityEd.setFilters(new InputFilter[]{new InputFilterMinMax(1, 10)});



        busbarViewModel.getText().observe(this, s -> textView.setText(s));
        return root;

    }





}