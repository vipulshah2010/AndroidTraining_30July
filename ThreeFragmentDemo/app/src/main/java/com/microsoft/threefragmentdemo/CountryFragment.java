package com.microsoft.threefragmentdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class CountryFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment_country, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button indiaButton = view.findViewById(R.id.indiaButton);
        Button australiaButton = view.findViewById(R.id.australiaButton);
        Button chinaButton = view.findViewById(R.id.chinaButton);

        indiaButton.setOnClickListener(this);
        australiaButton.setOnClickListener(this);
        chinaButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        CountryListener listener = (CountryListener) getActivity();
        if (listener != null) {
            switch (view.getId()) {
                case R.id.indiaButton:
                    listener.onCountrySelected("India");
                    break;
                case R.id.australiaButton:
                    listener.onCountrySelected("Australia");
                    break;
                case R.id.chinaButton:
                    listener.onCountrySelected("China");
                    break;
            }
        }
    }

    public interface CountryListener {
        void onCountrySelected(String country);
    }
}
