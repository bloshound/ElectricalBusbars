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
    private MutableLiveData<String> mQuantityM = new MutableLiveData<>();
    private LiveData<String> mQuantity = Transformations.map(mQuantityM, input -> input);

    private LiveData<String> mMaterial = Transformations.map(mBusbar, input -> input.getMaterial());
    private LiveData<String> mDensity = Transformations.map(mBusbar, input -> String.valueOf(input.getDensity()));
    private LiveData<String> mLength = Transformations.map(mBusbar, input -> String.valueOf(input.getLength()));
    private LiveData<String> mWidth = Transformations.map(mBusbar, input -> String.valueOf(input.getWidth()));
    private LiveData<String> mThickness = Transformations.map(mBusbar, input -> String.valueOf(input.getThickness()));



    public void setBusbar(Busbar busbar) {
        mBusbar.setValue(busbar);
    }

    public LiveData<String> getQuantity() {
        return mQuantity;
    }

    public void setQuantity(String quantity) {
        mQuantityM.setValue(quantity);
    }

    public LiveData<String> getMaterial() {
        return mMaterial;
    }

    public LiveData<String> getDensity() {
        return mDensity;
    }

    public LiveData<String> getLength() {
        return mLength;
    }

    public LiveData<String> getWidth() {
        return mWidth;
    }

    public LiveData<String> getThickness() {
        return mThickness;
    }

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }
}