package com.vipshah.sharedpreferencedemo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button readButton = findViewById(R.id.readButton);
        Button writeButton = findViewById(R.id.writeButton);
        Button clearButton = findViewById(R.id.clearButton);

        sharedPreferences = getSharedPreferences("MainActivity", MODE_PRIVATE);

        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedPreferences.contains("username")) {
                    String username = sharedPreferences.getString("username", "");
                    Toast.makeText(MainActivity.this, username, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().putString("username", "Sanket").apply();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().clear().apply();
            }
        });
    }
}
