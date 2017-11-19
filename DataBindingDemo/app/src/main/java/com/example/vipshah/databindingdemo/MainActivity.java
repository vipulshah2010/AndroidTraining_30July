package com.example.vipshah.databindingdemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

// https://docs.google.com/presentation/d/e/2PACX-1vRj3oddAMYT5vPlIciKg6YvTE0S8FrFTRbv9smHeylUMNuaev5L7CZyPgYNM8Ci1Qn4UmVFvKM0fp50/pub?start=true&loop=false&delayms=3000&slide=id.g1dbd4014ac_0_237

import com.example.vipshah.databindingdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_main);

        Person person = new Person();
        person.setName("Vishal");
        person.setAge(112);
        person.setLocation("Pune");
        binding.setPerson(person);
        binding.setHandler(new ClickHandler());
    }

    public class ClickHandler {
        public void onButtonClicked(View view, String title) {
            Toast.makeText(MainActivity.this, title, Toast.LENGTH_SHORT).show();
        }
    }
}
