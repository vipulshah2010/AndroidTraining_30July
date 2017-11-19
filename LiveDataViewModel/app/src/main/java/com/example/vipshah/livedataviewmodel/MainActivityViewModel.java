package com.example.vipshah.livedataviewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

public class MainActivityViewModel extends AndroidViewModel {

    MutableLiveData<Integer> PROGRESS = new MutableLiveData<>();

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        PROGRESS.setValue(0);
    }
}
