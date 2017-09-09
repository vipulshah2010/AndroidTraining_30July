package com.microsoft.roomexample;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface EmployeeDao {

    @Insert
    Long addEmployee(Employee employee);

    @Insert
    List<Long> addEmployees(Employee... employees);

    @Update
    int updateEmployee(Employee employee);

    @Delete
    int deleteEmployee(Employee employee);

    @Query("select * from employee")
    List<Employee> getEmployees();
}
