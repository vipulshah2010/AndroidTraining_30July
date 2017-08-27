package com.microsoft.recyclerviewdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.microsoft.recyclerviewdemo.grid.GridDemoFragment;
import com.microsoft.recyclerviewdemo.list.ListDemoFragment;
import com.microsoft.recyclerviewdemo.model.Country;

public class MainActivity extends AppCompatActivity implements ListDemoFragment.ListDemoFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        switch (i) {
            case R.id.menu_item_list:
                showFragment(new ListDemoFragment());
                return true;
            case R.id.menu_item_grid:
                showFragment(new GridDemoFragment());
                return true;
            default:
                return false;
        }
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }

    @Override
    public void onCountrySelected(Country country) {
        Toast.makeText(this, "You live in " + country.name + " from Activity!", Toast.LENGTH_SHORT).show();
    }
}
