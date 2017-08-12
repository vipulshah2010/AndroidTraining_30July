package com.microsoft.twoscreenapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    public static final String RESULT_VALUE = "result";

    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultTextView = findViewById(R.id.resultTextView);

        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString(MainActivity.KEY_USERNAME);

        if (TextUtils.isEmpty(username)) {
            setResult(RESULT_CANCELED, null);
            finish();
        } else {
            boolean isValidUser = username.equals("Vipul");

            resultTextView.setText(isValidUser ? "Success" : "Failure");

            Intent intent = new Intent();
            intent.putExtra(RESULT_VALUE, isValidUser);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
