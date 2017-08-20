package com.microsoft.fragmentbackstack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements Fragment1.Fragment1Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, new Fragment1())
                .addToBackStack(null)
                .commit();

        Button showDialogFragmentButton = findViewById(R.id.showDialogFragmentButton);

        class MyOnClickListener implements View.OnClickListener {
            @Override
            public void onClick(View view) {
                showDialogFragment();
            }
        }

        showDialogFragmentButton.setOnClickListener(new MyOnClickListener());
    }

    @Override
    public void onNumberEntered(String number) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentContainer, Fragment2.newInstance(number))
                .addToBackStack(null)
                .commit();
    }

    private void showDialogFragment() {
        new MyDialogFragment().show(getSupportFragmentManager(), null);
    }
}
