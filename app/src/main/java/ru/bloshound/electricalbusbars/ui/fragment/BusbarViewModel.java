package ru.bloshound.electricalbusbars.ui.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class BusbarViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, input -> "Hello world from section: " + input);

    private MutableLiveData<CharSequence> mQuantity = new MutableLiveData<>();
    private LiveData<Integer> mQuantityNumber =  Transformations.map(mQuantity, input -> Integer.parseInt(mQuantity.toString()));




    public void setQuantity(CharSequence quantity) {
        mQuantity.setValue(quantity);
    }

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }
}