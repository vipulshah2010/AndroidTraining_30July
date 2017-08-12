package com.microsoft.customdialog;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button showDialogButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showDialogButton = findViewById(R.id.showDialogButton);
        showDialogButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = builder.create();
        final View customView = LayoutInflater.from(this).inflate(R.layout.layout_dialog, null);

        final EditText usernameEditText = customView.findViewById(R.id.usernameEditText);
        final Button celebrateButton = customView.findViewById(R.id.celebrateButton);
        final Button donateButton = customView.findViewById(R.id.donateButton);

        celebrateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                Snackbar.make(showDialogButton, username + " is celebrating Lottery!", Snackbar.LENGTH_LONG).show();
                alertDialog.dismiss();
            }
        });

        donateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                Snackbar.make(showDialogButton, username + " is donating Lottery!", Snackbar.LENGTH_LONG).show();
                alertDialog.dismiss();
            }
        });

        alertDialog.setView(customView);
        alertDialog.show();
    }
}
