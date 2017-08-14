package com.microsoft.threefragmentdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CountryFragment.CountryListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.countriesContainer, new CountryFragment())
                .commit();
    }

    @Override
    public void onCountrySelected(String country) {
        ArrayList<String> cities = new ArrayList<>();
        switch (country) {
            case "India":
                cities.add("Mumbai");
                cities.add("Delhi");
                cities.add("Chennai");
                break;
            case "Australia":
                cities.add("Sydney");
                cities.add("Dubbo");
                cities.add("Bathurst");
                break;
            case "China":
                cities.add("Beijing");
                cities.add("Chongqing");
                cities.add("Shanghai");
                break;
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.citiesContainer, CitiesFragment.newInstance(cities))
                .commit();
    }
}
