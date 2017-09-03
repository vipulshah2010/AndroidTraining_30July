package com.microsoft.searchviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RecyclerView countriesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        countriesRecyclerView = findViewById(R.id.countriesRecyclerView);
        countriesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<String> countries = new ArrayList<>();
        countries.add("India");
        countries.add("Australia");
        countries.add("Pakistan");
        countries.add("China");
        countries.add("Japan");
        countries.add("Jamaica");
        countries.add("Chile");

        CountryAdapter adapter = new CountryAdapter(countries);
        countriesRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String searchText) {
        if (countriesRecyclerView.getAdapter() != null
                && countriesRecyclerView.getAdapter() instanceof CountryAdapter) {
            CountryAdapter adapter = (CountryAdapter) countriesRecyclerView.getAdapter();
            adapter.getFilter().filter(searchText);
            return true;
        }
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }
}
