package ru.bloshound.electricalbusbars.ui.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
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
import ru.bloshound.electricalbusbars.util.SlliderObserver;

/**
 * A placeholder fragment containing a simple view.
 */
public class BusbarHolderFragment extends Fragment {


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
        busbarViewModel.setBusBar(busbar);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main, container, false);

        MutableLiveData<Busbar> busbarLD = busbarViewModel.getBusbar();
        MutableLiveData<String> quantityLD = busbarViewModel.getQuantity();

        final TextView textView = (TextView) root.findViewById(R.id.section_label);

        Slider quantitySlider = (Slider) root.findViewById(R.id.slider_quantity);
        Slider lengthSlider = (Slider) root.findViewById(R.id.slider_length);
        Slider widthSlider = (Slider) root.findViewById(R.id.slider_width);
        Slider thicknessSlider = (Slider) root.findViewById(R.id.slider_thickness);

        EditText quantityEditText = (EditText) root.findViewById(R.id.ed_quantity);
        EditText lengthEditText = (EditText) root.findViewById(R.id.ed_length);
        EditText widthEditText = (EditText) root.findViewById(R.id.ed_width);
        EditText thicknessEditText = (EditText) root.findViewById(R.id.ed_thickness);

        Observer<String> quantityObserver = new SlliderObserver(quantitySlider);

        quantityEditText.setFilters(new InputFilter[]{new InputFilterMinMax(1, 10)});
        quantityLD.observe(this, quantityObserver);
        quantityEditText.addTextChangedListener(new AfterTextChangeWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                quantityLD.setValue(s.toString());
            }
        });

        Observer<Busbar> lengthObserver = s -> {
            int i = s.getLength();
            String sl = String.valueOf(i);
            if (TextUtils.isDigitsOnly(sl) && !TextUtils.isEmpty(sl)) {
                if (i >= lengthSlider.getValueFrom() && i <= lengthSlider.getValueTo())
                    lengthSlider.setValue(i);
            }
        };

        lengthEditText.setFilters(new InputFilter[]{new InputFilterMinMax(1, 500)});


        busbarViewModel.getText().observe(this, s -> textView.setText(s));
        return root;

    }
}