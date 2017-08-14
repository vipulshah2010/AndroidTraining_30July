package com.microsoft.threefragmentdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class CitiesFragment extends Fragment {

    private static final String KEY_CITIES = "cities";

    public static CitiesFragment newInstance(ArrayList<String> cities) {

        Bundle bundle = new Bundle();
        bundle.putStringArrayList(KEY_CITIES, cities);
        CitiesFragment fragment = new CitiesFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment_cities, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        ArrayList<String> cities = bundle.getStringArrayList(KEY_CITIES);
        Button city1Button = view.findViewById(R.id.city1Button);
        Button city2Button = view.findViewById(R.id.city2Button);
        Button city3Button = view.findViewById(R.id.city3Button);

        city1Button.setText(cities.get(0));
        city2Button.setText(cities.get(1));
        city3Button.setText(cities.get(2));
    }
}
