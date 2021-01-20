package ru.bloshound.electricalbusbars.util.slider;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import androidx.lifecycle.MutableLiveData;
import ru.bloshound.electricalbusbars.model.AluminiumBusbar;
import ru.bloshound.electricalbusbars.model.Busbar;
import ru.bloshound.electricalbusbars.model.CopperBusbar;

public class ModelChangeTextWatcher implements TextWatcher {

    MutableLiveData<Busbar> busbarLiveData;
    Variable var;


    public ModelChangeTextWatcher(MutableLiveData<Busbar> busbarLiveData, Variable var) {
        this.busbarLiveData = busbarLiveData;
        this.var = var;
    }

    public enum Variable {Density, Length, Width, Thickness}

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        Busbar busbar = busbarLiveData.getValue();
        Busbar newBasbar;

        if (busbar.getMaterial().equalsIgnoreCase("copper")) {
            newBasbar = new CopperBusbar(busbar);
        }
        if(busbar.getMaterial().equalsIgnoreCase("aluminium")){
            newBasbar = new AluminiumBusbar(busbar);
        }
        int value = 1;
        if(!TextUtils.isEmpty(s) && TextUtils.isDigitsOnly(s)){
            value = Integer.parseInt(s.toString());
        }

        switch (var){
            case Density: busbar.setDensity(value);
            break;
            case Length: busbar.setLength(value);
            break;
            case Width: busbar.setWidth(value);
            break;
            case Thickness: busbar.setThickness(value);
            break;
        }

        busbarLiveData.setValue(busbar);


    }


}

