package com.example.vipshah.espressodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.number1EditText)
    EditText number1EditText;

    @BindView(R.id.number2EditText)
    EditText number2EditText;

    @BindView(R.id.answerTextView)
    TextView answerTextView;

    @Inject
    Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        car.getSpeed();
    }

    @OnClick(R.id.addButton)
    void add() {
        String number1 = number1EditText.getText().toString();
        String number2 = number2EditText.getText().toString();
        if (TextUtils.isEmpty(number1) || TextUtils.isEmpty(number2)) {
            answerTextView.setText("Please input both numbers!");
        } else {
            float answer = Float.parseFloat(number1) + Float.parseFloat(number2);
            answerTextView.setText(String.valueOf(answer));
        }
    }

    @OnClick(R.id.subtractButton)
    void subtract() {
        String number1 = number1EditText.getText().toString();
        String number2 = number2EditText.getText().toString();
        if (TextUtils.isEmpty(number1) || TextUtils.isEmpty(number2)) {
            answerTextView.setText("Please input both numbers!");
        } else {
            float answer = Float.parseFloat(number1) - Float.parseFloat(number2);
            answerTextView.setText(String.valueOf(answer));
        }
    }

    @OnClick(R.id.multiplyButton)
    void multiply() {
        String number1 = number1EditText.getText().toString();
        String number2 = number2EditText.getText().toString();
        if (TextUtils.isEmpty(number1) || TextUtils.isEmpty(number2)) {
            answerTextView.setText("Please input both numbers!");
        } else {
            float answer = Float.parseFloat(number1) * Float.parseFloat(number2);
            answerTextView.setText(String.valueOf(answer));
        }
    }

    @OnClick(R.id.divideButton)
    void divide() {
        String number1 = number1EditText.getText().toString();
        String number2 = number2EditText.getText().toString();
        if (TextUtils.isEmpty(number1) || TextUtils.isEmpty(number2)) {
            answerTextView.setText("Please input both numbers!");
        } else {
            float answer = Float.parseFloat(number1) / Float.parseFloat(number2);
            answerTextView.setText(String.valueOf(answer));
        }
    }


}
