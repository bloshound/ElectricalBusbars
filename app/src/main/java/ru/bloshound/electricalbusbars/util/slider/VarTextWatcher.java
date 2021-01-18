package ru.bloshound.electricalbusbars.util.slider;

import android.text.Editable;
import android.text.TextWatcher;

public class VarTextWatcher implements TextWatcher {

    CharSequence sharS;

    public VarTextWatcher(CharSequence sharS) {
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
        sharS = s.toString();
    }
}
