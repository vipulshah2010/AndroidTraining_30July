package com.example.vipshah.viewmodelprovidersdemo;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button setPersonButton = findViewById(R.id.setPersonButton);
        Button getPersonButton = findViewById(R.id.getPersonButton);

        setPersonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPerson();
            }
        });

        getPersonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPerson();
            }
        });
    }

    private void setPerson() {
        PersonViewModel personViewModel = ViewModelProviders.of(this)
                .get(PersonViewModel.class);

        Person person1 = new Person();
        person1.setName("Vipul");
        person1.setAge(21);
        person1.setLocation("Mumbai");

        List<Person> persons = new ArrayList<>();
        persons.add(person1);

        personViewModel.setPersons(persons);
    }

    private void getPerson() {
        PersonViewModel personViewModel = ViewModelProviders.of(this)
                .get(PersonViewModel.class);

        if (personViewModel.getPersons() != null) {
            List<Person> persons = personViewModel.getPersons();
            for (Person person : persons) {
                Toast.makeText(this, person.getName() + " " + person.getAge() + " " +
                                person.getLocation(),
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}
