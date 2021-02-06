package ru.bloshound.electricalbusbars.util.textedit;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

public abstract class AfterChangeTextWatcher implements TextWatcher {

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public abstract void afterTextChanged(Editable s);
}
