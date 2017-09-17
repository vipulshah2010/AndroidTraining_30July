package com.microsoft.roomexample;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

@Entity(tableName = "employee")
public class Employee implements Parcelable {

    public static final Parcelable.Creator<Employee> CREATOR = new Parcelable.Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel source) {
            return new Employee(source);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

    final static String CRICKET = "cricket";
    final static String HOCKEY = "hockey";
    final static String FOOTBALL = "football";

    @PrimaryKey(autoGenerate = true)
    private long _id;
    private String name;
    private int age;
    private String location;

    @Ignore
    @TypeConverters({HobbyConverter.class})
    private List<String> hobbies;

    Employee() {
    }

    private Employee(Parcel in) {
        this._id = in.readLong();
        this.name = in.readString();
        this.age = in.readInt();
        this.location = in.readString();
        this.hobbies = in.createStringArrayList();
    }

    public long getId() {
        return _id;
    }

    public void setId(long _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this._id);
        dest.writeString(this.name);
        dest.writeInt(this.age);
        dest.writeString(this.location);
        dest.writeStringList(hobbies);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        return _id == employee._id;
    }

    @Override
    public int hashCode() {
        return (int) (_id ^ (_id >>> 32));
    }
}
