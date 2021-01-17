package ru.bloshound.electricalbusbars.util;

import android.text.Editable;
import android.text.TextWatcher;

import androidx.lifecycle.MutableLiveData;


public class LiveDataTextWatcher implements TextWatcher {

    private MutableLiveData<String> ld;

    public LiveDataTextWatcher(MutableLiveData<String> ld) {
        super();
        this.ld = ld;
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        ld.setValue(s.toString());
    }

}
