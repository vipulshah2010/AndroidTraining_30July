package com.example.vipshah.viewmodelprovidersdemo;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

public class PersonLiveDataViewModel extends ViewModel {

    private MutableLiveData<List<Person>> mPersons;

    public MutableLiveData<List<Person>> getPersons() {
        if (mPersons == null) {
            mPersons = new MutableLiveData<>();
        }
        return mPersons;
    }

    public void setPersons(List<Person> persons) {
        if (mPersons == null) {
            mPersons = new MutableLiveData<>();
        }
        mPersons.setValue(persons);
    }
}
