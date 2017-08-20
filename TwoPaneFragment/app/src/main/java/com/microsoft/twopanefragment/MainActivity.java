package com.microsoft.twopanefragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements Fragment1.Fragment1Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment1Container, new Fragment1())
                .commit();
    }

    @Override
    public void onColorSelected(String color) {
        View fragment2Container = findViewById(R.id.fragment2Container);

        if (fragment2Container != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment2Container, Fragment2.newInstance(color))
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment1Container, Fragment2.newInstance(color))
                    .addToBackStack(null)
                    .commit();
        }
    }
}
