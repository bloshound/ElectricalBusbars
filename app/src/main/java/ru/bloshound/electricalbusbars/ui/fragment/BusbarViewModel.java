package ru.bloshound.electricalbusbars.ui.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import ru.bloshound.electricalbusbars.model.Busbar;

public class BusbarViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, input -> "Hello world from section: " + input);

    private MutableLiveData<Busbar> mBusbar = new MutableLiveData<>();

    private MutableLiveData<String> mQuantityM = new MutableLiveData<>();

    public void setBusbar(Busbar busbar){
        mBusbar.setValue(busbar);
    }

    public MutableLiveData<Busbar> getBusbar(){
        return mBusbar;
    }


    public void setIndex(int index){
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }
}