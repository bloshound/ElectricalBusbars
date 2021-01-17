package ru.bloshound.electricalbusbars.ui.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import ru.bloshound.electricalbusbars.repo.Busbar;

public class BusbarViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, input -> "Hello world from section: " + input);

    private MutableLiveData<Busbar> mBusbar = new MutableLiveData<>();
    private LiveData<String> mMaterial = Transformations.map(mBusbar, input -> input.getMaterial());
    private LiveData<String> mDensity = Transformations.map(mBusbar, input ->  String.valueOf(input.getDensity()));
    private LiveData<String> mLength = Transformations.map(mBusbar, input -> String.valueOf(input.getLength()));
    private LiveData<String> mWidth = Transformations.map(mBusbar,input -> String.valueOf(input.getWidth()));
    private LiveData<String> mThickness = Transformations.map(mBusbar, input -> String.valueOf(input.getThickness()));
    private LiveData<String> mSection = Transformations.map(mBusbar, input -> String.valueOf(input.getSection()));

    private MutableLiveData<String> mQuantity = new MutableLiveData<>();





    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }
}