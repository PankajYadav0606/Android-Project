package com.example.viewmodel;

import android.renderscript.BaseObj;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class MyViewModel extends BaseObservable {

    int counter;


    @Bindable
    public int getCounter() {
        return counter;
    }

    public void onIncrease(){

        counter++;
        notifyPropertyChanged(BR.counter);
    }

    public void onDecrease(){
        counter--;
        notifyPropertyChanged(BR.counter);
    }
}
