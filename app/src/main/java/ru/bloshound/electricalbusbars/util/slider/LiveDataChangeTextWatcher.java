package ru.bloshound.electricalbusbars.util.slider;

import android.text.Editable;
import android.text.TextWatcher;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class LiveDataChangeTextWatcher implements TextWatcher {

    MutableLiveData<CharSequence> sharS;

    public LiveDataChangeTextWatcher(MutableLiveData<CharSequence> sharS) {
        this.sharS = sharS;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        sharS.setValue(s.toString());
        System.out.println(s);
    }
}
