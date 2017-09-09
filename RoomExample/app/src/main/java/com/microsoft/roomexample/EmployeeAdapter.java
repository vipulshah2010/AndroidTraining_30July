package com.microsoft.roomexample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private List<Employee> employees;
    private EmployeeListener employeeListener;

    EmployeeAdapter() {
        employees = new ArrayList<>();
    }

    public void setEmployeeListener(EmployeeListener employeeListener) {
        this.employeeListener = employeeListener;
    }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_employee, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final EmployeeViewHolder holder, int position) {
        final Employee employee = employees.get(position);
        holder.nameTextView.setText(employee.getName());
        holder.ageTextView.setText(String.valueOf(employee.getAge()));
        holder.locationTextView.setText(employee.getLocation());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Employee selectedEmployee = employees.get(holder.getAdapterPosition());

                if (employeeListener != null) {
                    employeeListener.onEmployeeSelected(selectedEmployee);
                }
            }
        });
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    void setEmployees(List<Employee> employeeList) {
        employees.addAll(employeeList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    void onEmployeeDeleted(Employee employee) {
        employees.remove(employee);
        notifyDataSetChanged();
    }

    void onEmployeeAdded(Employee employee) {
        employees.add(employee);
        notifyDataSetChanged();
    }

    void onEmployeeUpdated(Employee employee) {
        employees.set(employees.indexOf(employee), employee);
        notifyDataSetChanged();
    }

    public interface EmployeeListener {
        void onEmployeeSelected(Employee employee);
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {

        final TextView nameTextView;
        final TextView ageTextView;
        final TextView locationTextView;

        EmployeeViewHolder(View view) {
            super(view);

            nameTextView = view.findViewById(R.id.nameTextView);
            ageTextView = view.findViewById(R.id.ageTextView);
            locationTextView = view.findViewById(R.id.locationTextView);
        }
    }
}
