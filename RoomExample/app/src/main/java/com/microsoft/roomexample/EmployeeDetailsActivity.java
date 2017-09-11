package com.microsoft.roomexample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDetailsActivity extends AppCompatActivity {

    public static final String ARG_EMPLOYEE = "Employee";
    public static final String ARG_IS_UPDATE = "is_update";

    private EditText nameEditText;
    private EditText ageEditText;
    private EditText locationEditText;

    private CheckBox cricketCheckbox;
    private CheckBox hockeyCheckbox;
    private CheckBox footballCheckbox;

    public static Intent getIntent(Context context) {
        return new Intent(context, EmployeeDetailsActivity.class);
    }

    public static Intent getIntent(Context context, Employee employee) {
        Intent intent = new Intent(context, EmployeeDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_EMPLOYEE, employee);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);

        nameEditText = findViewById(R.id.nameEditText);
        ageEditText = findViewById(R.id.ageEditText);
        locationEditText = findViewById(R.id.locationEditText);

        cricketCheckbox = findViewById(R.id.cricketCheckbox);
        hockeyCheckbox = findViewById(R.id.hockeyCheckbox);
        footballCheckbox = findViewById(R.id.footballCheckbox);

        Button addUpdateButton = findViewById(R.id.addUpdateButton);
        Button deleteButton = findViewById(R.id.deleteButton);

        if (getEmployee() != null) {
            Employee employee = getEmployee();
            addUpdateButton.setText(R.string.update);
            deleteButton.setVisibility(View.VISIBLE);
            nameEditText.setText(employee.getName());
            ageEditText.setText(String.valueOf(employee.getAge()));
            locationEditText.setText(employee.getLocation());

            cricketCheckbox.setChecked(employee.getHobbies().contains(Employee.CRICKET));
            hockeyCheckbox.setChecked(employee.getHobbies().contains(Employee.HOCKEY));
            footballCheckbox.setChecked(employee.getHobbies().contains(Employee.FOOTBALL));
        } else {
            deleteButton.setVisibility(View.GONE);
            addUpdateButton.setText(R.string.add);
        }

        addUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEditText.getText().toString();
                int age = Integer.parseInt(ageEditText.getText().toString());
                String location = locationEditText.getText().toString();
                Employee employee;

                Intent intent = new Intent();

                if (getEmployee() != null) {
                    employee = getEmployee();
                    intent.putExtra(ARG_IS_UPDATE, true);
                } else {
                    employee = new Employee();
                }

                employee.setName(name);
                employee.setAge(age);
                employee.setLocation(location);

                List<String> hobbies = new ArrayList<>();

                if (cricketCheckbox.isChecked()) {
                    hobbies.add(Employee.CRICKET);
                }
                if (hockeyCheckbox.isChecked()) {
                    hobbies.add(Employee.HOCKEY);
                }
                if (footballCheckbox.isChecked()) {
                    hobbies.add(Employee.FOOTBALL);
                }

                employee.setHobbies(hobbies);

                intent.putExtra(ARG_EMPLOYEE, employee);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(ARG_EMPLOYEE, getEmployee());
                intent.putExtra(ARG_IS_UPDATE, false);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private Employee getEmployee() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null && bundle.containsKey(ARG_EMPLOYEE)) {
            return bundle.getParcelable(ARG_EMPLOYEE);
        }
        return null;
    }
}
