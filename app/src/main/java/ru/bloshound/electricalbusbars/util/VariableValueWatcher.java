package ru.bloshound.electricalbusbars.util;

import android.text.Editable;
import android.text.TextWatcher;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


public class VariableValueWatcher implements TextWatcher {

    CharSequence variable;

    public VariableValueWatcher(CharSequence variable) {
        super();
        this.variable = variable;

    }

    public void setVariable(CharSequence variable) {
        this.variable = variable;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        variable = s.toString();
    }
}
