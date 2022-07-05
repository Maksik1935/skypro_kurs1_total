package com.example.skypro_collections1.service;

import com.example.skypro_collections1.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface EmployeeService {
    boolean addEmployee(String firstName, String lastName, Integer salary, Integer department);
    public boolean removeEmployee(String firstName, String lastName);
    public Employee findEmployee(String firstName, String lastName);
    public Map<String, Employee> getEmployees();


}
