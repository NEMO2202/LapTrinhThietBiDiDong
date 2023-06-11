package com.example.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Employee implements Serializable {
    String EmployeeId;
    String EmployeeName;
    Integer EmployeeAge;

    public String getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(String employeeId) {
        EmployeeId = employeeId;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public Integer getEmployeeAge() {
        return EmployeeAge;
    }

    public void setEmployeeAge(Integer employeeAge) {
        EmployeeAge = employeeAge;
    }

    public Employee(String employeeId, String employeeName, Integer employeeAge) {
        this.EmployeeId = employeeId;
        this.EmployeeName = employeeName;
        this.EmployeeAge = employeeAge;
    }

    @NonNull
    @Override
    public String toString() {
        return EmployeeName;
    }
}
