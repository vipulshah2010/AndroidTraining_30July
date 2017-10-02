package com.example.vipshah.viewmodelprovidersdemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LiveDataMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button setPersonButton = findViewById(R.id.setPersonButton);
        Button getPersonButton = findViewById(R.id.getPersonButton);

        getPersonButton.setVisibility(View.GONE);

        final PersonLiveDataViewModel personViewModel = ViewModelProviders.of(this)
                .get(PersonLiveDataViewModel.class);

        personViewModel.getPersons().observeForever(new Observer<List<Person>>() {
            @Override
            public void onChanged(@Nullable List<Person> persons) {
                for (Person person : persons) {
                    Toast.makeText(LiveDataMainActivity.this, person.getName() +
                                    " " + person.getAge() + " " +
                                    person.getLocation(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        setPersonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Person person1 = new Person();
                person1.setName("Vipul");
                person1.setAge(21);
                person1.setLocation("Mumbai");

                List<Person> persons = new ArrayList<>();
                persons.add(person1);

                personViewModel.setPersons(persons);
            }
        });

    }
}
