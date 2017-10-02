package com.example.vipshah.viewmodelprovidersdemo;

import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class PersonViewModel extends ViewModel {

    private List<Person> mPersons;

    public List<Person> getPersons() {
        if (mPersons == null) {
            mPersons = new ArrayList<>();
        }
        return mPersons;
    }

    public void setPersons(List<Person> persons) {
        mPersons = persons;
    }
}
