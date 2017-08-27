package com.microsoft.recyclerviewdemo.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.microsoft.recyclerviewdemo.R;
import com.microsoft.recyclerviewdemo.model.Country;

import java.util.ArrayList;
import java.util.List;

// Items => Adapter => RecyclerView
// Jeans => Amazon => Vipul

public class ListDemoFragment extends Fragment
        implements CountriesListAdapter.OnCountrySelectedListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_demo, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Country> countries = new ArrayList<>();

        countries.add(new Country("India", R.drawable.in));
        countries.add(new Country("China", R.drawable.cn));
        countries.add(new Country("Canada", R.drawable.ca));
        countries.add(new Country("New Zealand", R.drawable.nz));
        countries.add(new Country("United States", R.drawable.us));

        CountriesListAdapter adapter = new CountriesListAdapter(countries);
        adapter.setListener(this);
        RecyclerView countriesListRecyclerView = view.
                findViewById(R.id.countriesListRecyclerView);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        countriesListRecyclerView.setLayoutManager(manager);
        countriesListRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onCountrySelected(Country country) {
        Toast.makeText(getActivity(), "You live in " + country.name, Toast.LENGTH_SHORT).show();

        if (getActivity() instanceof ListDemoFragmentListener) {
            ListDemoFragmentListener listener = (ListDemoFragmentListener) getActivity();
            listener.onCountrySelected(country);
        }
    }

    public interface ListDemoFragmentListener {
        void onCountrySelected(Country country);
    }
}
