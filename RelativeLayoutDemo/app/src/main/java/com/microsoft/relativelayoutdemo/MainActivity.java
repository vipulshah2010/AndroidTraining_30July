package com.microsoft.relativelayoutdemo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DialogInterface.OnClickListener {

    AlertDialog confirmDialog;
    AlertDialog greetConfirmDialog;

    private boolean didGreet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendship);

        Button greetFriendButton = findViewById(R.id.greetFriendButton);
        Button exitButton = findViewById(R.id.exitButton);

        greetFriendButton.setOnClickListener(this);
        exitButton.setOnClickListener(this);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("didGreet")) {
                didGreet = savedInstanceState.getBoolean("didGreet");
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("didGreet", didGreet);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.greetFriendButton:
                didGreet = true;
                showGreetToastMessage();
                break;
            case R.id.exitButton:
                if (didGreet) {
                    confirmExit();
                } else {
                    requestGreeting();
                }
                break;
        }
    }

    private void showGreetToastMessage() {
        Toast toast = Toast.makeText(this, "Happy Friendship Day!",
                Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    private void confirmExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.alert_title);
        builder.setMessage(R.string.alert_message);
        builder.setPositiveButton(R.string.yes, this);
        builder.setNegativeButton(R.string.no, null);
        confirmDialog = builder.create();
        confirmDialog.show();
    }

    private void requestGreeting() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.greet_your_friends_title);
        builder.setMessage(R.string.greet_your_friends);
        builder.setPositiveButton(R.string.yes, this);
        builder.setNegativeButton(R.string.no, null);
        greetConfirmDialog = builder.create();
        greetConfirmDialog.show();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int position) {
        if (dialogInterface == confirmDialog) {
            if (position == DialogInterface.BUTTON_POSITIVE) {
                finish();
            }
        }
    }
}
