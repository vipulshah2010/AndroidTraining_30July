package com.vipshah.retrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public abstract class BaseActivity extends AppCompatActivity {

    protected void handleError(Throwable t) {
        t.printStackTrace();
        Toast.makeText(BaseActivity.this, "Failed!!", Toast.LENGTH_SHORT)
                .show();
    }
}
