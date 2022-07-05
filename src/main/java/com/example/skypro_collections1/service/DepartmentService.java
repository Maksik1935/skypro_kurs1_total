package com.example.skypro_collections1.service;

import com.example.skypro_collections1.Employee;

import java.util.Map;
import java.util.Set;

public interface DepartmentService {
    Employee getEmployeeWithMaxSalaryByDepartment(Integer department);

    Employee getEmployeeWithMinSalaryByDepartment(Integer department);

    Set<Employee> getAllEmployeeByDepartment(Integer department);

    Map<String, String> getAllEmployees();
}
