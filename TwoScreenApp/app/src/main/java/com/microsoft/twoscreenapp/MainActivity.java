package com.microsoft.twoscreenapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String KEY_USERNAME = "username";
    private static final int REQUEST_CODE_LOGIN = 111;
    private EditText usernameEditText;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.usernameEditText);
        resultTextView = findViewById(R.id.resultTextView);
        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        String username = usernameEditText.getText().toString();

        Intent intent = new Intent(this, ResultActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(KEY_USERNAME, username);
        intent.putExtras(bundle);
        startActivityForResult(intent, REQUEST_CODE_LOGIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_CODE_LOGIN) {
            if (resultCode == RESULT_OK) {
                boolean isValidUser = intent.getBooleanExtra(ResultActivity.RESULT_VALUE, false);
                resultTextView.setText(isValidUser ? "Success!" : "Failure!");
            } else {
                resultTextView.setText("Please Provide Username");
            }
        }
    }
}
