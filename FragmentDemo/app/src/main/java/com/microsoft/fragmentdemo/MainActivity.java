package com.microsoft.fragmentdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import static com.microsoft.fragmentdemo.ColorFragment.ColorFragmentListener;

public class MainActivity extends AppCompatActivity implements ColorFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.colorFragmentContainer, new ColorFragment())
                .commit();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.colorDescFragment, ColorDescFragment.newInstance("#ff0000"))
                .commit();
    }

    @Override
    public void onColorSelected(String colorName) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.colorDescFragment, ColorDescFragment.newInstance(colorName))
                .commit();
    }
}
