package com.microsoft.recyclerviewdemo.grid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.microsoft.recyclerviewdemo.R;
import com.microsoft.recyclerviewdemo.model.Country;

import java.util.ArrayList;
import java.util.List;

// Items => Adapter => RecyclerView
// Jeans => Amazon => Vipul

public class GridDemoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_grid_demo, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView countriesGridRecyclerView = view.findViewById(R.id.countriesGridRecyclerView);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(), 2);
        countriesGridRecyclerView.setLayoutManager(manager);
        List<Country> countries = new ArrayList<>();

        countries.add(new Country("India", R.drawable.in));
        countries.add(new Country("China", R.drawable.cn));
        countries.add(new Country("Canada", R.drawable.ca));
        countries.add(new Country("New Zealand", R.drawable.nz));
        countries.add(new Country("United States", R.drawable.us));

        CountriesGridAdapter adapter = new CountriesGridAdapter(countries);
        countriesGridRecyclerView.setAdapter(adapter);
    }
}
