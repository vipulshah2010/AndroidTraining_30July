package com.example.vipshah.livedataviewmodel;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public MainActivityViewModel activityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityViewModel = ViewModelProviders.of(this)
                .get(MainActivityViewModel.class);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment1Container, SeekBarControlFragment.newInstance(activityViewModel.PROGRESS.getValue()))
                .commit();

        activityViewModel.PROGRESS.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer progress) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment2Container, SeekBarProgressFragment.newInstance(progress))
                        .commit();
            }
        });
    }
}
