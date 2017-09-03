package com.microsoft.navigationviewwithsvg;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.navigationView);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawer(Gravity.START);
        }
        item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.menu_action_currency:
                displayFragment(UnitType.WEIGHT);
                return true;
            case R.id.menu_action_lengths:
                displayFragment(UnitType.LENGTH);
                return true;
            case R.id.menu_action_weights:
                displayFragment(UnitType.WEIGHT);
                return true;
            default:
                return false;
        }
    }

    private void displayFragment(UnitType type) {
        SimpleFragment fragment = SimpleFragment.newInstance(type);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contentView, fragment)
                .commit();
    }


    public enum UnitType {
        LENGTH, WEIGHT, CURRENCY
    }
}
