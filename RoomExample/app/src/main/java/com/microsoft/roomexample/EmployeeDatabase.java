package com.microsoft.roomexample;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(version = 1, entities = Employee.class)
public abstract class EmployeeDatabase extends RoomDatabase {
    public abstract EmployeeDao employeeDao();
}
