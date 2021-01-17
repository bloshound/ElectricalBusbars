package ru.bloshound.electricalbusbars.util;

import android.text.Editable;
import android.text.TextWatcher;

import androidx.lifecycle.MutableLiveData;

public class AfterTextChangeWatcher implements TextWatcher {

    MutableLiveData<String> liveData;

    public AfterTextChangeWatcher(MutableLiveData<String> liveData) {
        this.liveData = liveData;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s){
        liveData.setValue(s.toString());
    }
}
