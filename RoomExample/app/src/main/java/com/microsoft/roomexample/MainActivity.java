package com.microsoft.roomexample;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD_EMPLOYEE = 10001;
    private static final int REQUEST_CODE_UPDATE_DELETE_EMPLOYEE = 10002;

    EmployeeDatabase database;

    private Button addEmployeeButton;
    private RecyclerView employeesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        employeesRecyclerView = findViewById(R.id.employeesRecyclerView);
        addEmployeeButton = findViewById(R.id.addEmployeeButton);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        employeesRecyclerView.setLayoutManager(manager);

        EmployeeAdapter adapter = new EmployeeAdapter();

        employeesRecyclerView.setAdapter(adapter);

        database =
                Room.databaseBuilder(this, EmployeeDatabase.class, "emp.db")
                        .allowMainThreadQueries()
                        .build();

        fetchAllEmployees();

        addEmployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = EmployeeDetailsActivity.getIntent(MainActivity.this);
                startActivityForResult(intent, REQUEST_CODE_ADD_EMPLOYEE);
            }
        });

        adapter.setEmployeeListener(new EmployeeAdapter.EmployeeListener() {
            @Override
            public void onEmployeeSelected(Employee employee) {
                Intent intent = EmployeeDetailsActivity.getIntent(MainActivity.this, employee);
                startActivityForResult(intent, REQUEST_CODE_UPDATE_DELETE_EMPLOYEE);
            }
        });
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItem = menu.findItem(R.id.action_add_employee);
        menuItem.setVisible(employeesRecyclerView.getVisibility() == View.VISIBLE);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_employee) {
            Intent intent = EmployeeDetailsActivity.getIntent(MainActivity.this);
            startActivityForResult(intent, REQUEST_CODE_ADD_EMPLOYEE);
            return true;
        }
        return false;
    }

    private void fetchAllEmployees() {
        List<Employee> employees = database.employeeDao().getEmployees();
        if (employees == null || employees.size() == 0) {
            employeesRecyclerView.setVisibility(View.GONE);
            addEmployeeButton.setVisibility(View.VISIBLE);
        } else {
            employeesRecyclerView.setVisibility(View.VISIBLE);
            addEmployeeButton.setVisibility(View.GONE);

            EmployeeAdapter adapter = (EmployeeAdapter) employeesRecyclerView.getAdapter();
            adapter.setEmployees(employees);
        }
        invalidateOptionsMenu();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && data != null) {
            EmployeeAdapter adapter = (EmployeeAdapter) employeesRecyclerView.getAdapter();
            if (requestCode == REQUEST_CODE_ADD_EMPLOYEE) {

                Employee employee = data.getParcelableExtra(EmployeeDetailsActivity.ARG_EMPLOYEE);
                database.employeeDao().addEmployee(employee);
                adapter.onEmployeeAdded(employee);
            } else {
                Employee employee = data.getParcelableExtra(EmployeeDetailsActivity.ARG_EMPLOYEE);
                boolean isUpdate = data.getBooleanExtra(EmployeeDetailsActivity.ARG_IS_UPDATE, true);

                if (isUpdate) {
                    database.employeeDao().updateEmployee(employee);
                    adapter.onEmployeeUpdated(employee);
                } else {
                    database.employeeDao().deleteEmployee(employee);
                    adapter.onEmployeeDeleted(employee);
                }
            }

            if (adapter.getEmployees().size() == 0) {
                employeesRecyclerView.setVisibility(View.GONE);
                addEmployeeButton.setVisibility(View.VISIBLE);
            } else {
                employeesRecyclerView.setVisibility(View.VISIBLE);
                addEmployeeButton.setVisibility(View.GONE);
            }

            invalidateOptionsMenu();
        }
    }
}
