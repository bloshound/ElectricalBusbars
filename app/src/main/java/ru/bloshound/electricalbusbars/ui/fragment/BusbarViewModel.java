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

    private MutableLiveData<String> mQuantityString = new MutableLiveData<>();


    public void setBusBar(Busbar busbar) {
        mBusbar.setValue(busbar);
    }

    public MutableLiveData<Busbar> getBusbar() {
        return mBusbar;
    }

    public void setQuantity(String quantity) {
        mQuantityString.setValue(quantity);
    }

    public MutableLiveData<String> getQuantity() {
        return mQuantityString;
    }


    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }
}