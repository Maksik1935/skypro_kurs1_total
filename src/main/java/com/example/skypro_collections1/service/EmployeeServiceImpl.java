package com.example.skypro_collections1.service;

import com.example.skypro_collections1.Employee;
import com.example.skypro_collections1.exceptions.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private Map<String, Employee> employees = new HashMap<>();

    @Override
    public boolean checkName(String firstName, String lastName) {
        if(StringUtils.isAlphaSpace(firstName) && StringUtils.isAlphaSpace(lastName)){
            return true;
        } else{
            throw new IncorrectNameException();
        }

    }

    private String toUpperCase(String str) {
        if(str != null) {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        } else {
            return str;
        }
    }


    @Override
    public boolean addEmployee(String firstName, String lastName, Integer salary, Integer department) {
        checkName(firstName, lastName);
        if(!StringUtils.isNumeric(salary.toString())) {
            throw new IncorrectSalaryExceprion();
        }
        if(!StringUtils.isNumeric(department.toString())) {
            throw new IncorrectDepartmentException();
        }
        firstName = toUpperCase(firstName);
        lastName = toUpperCase(lastName);
        Employee employee = new Employee(firstName, lastName, salary, department);
        if(employees.containsKey(employee.getFirstName() + employee.getLastName())) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(employee.getFirstName() + employee.getLastName(), employee);
        return true;
        }

    @Override
    public boolean removeEmployee(String firstName, String lastName) {
        checkName(firstName, lastName);
        Employee employee = new Employee(firstName, lastName, null, null);
        if(!employees.containsKey(employee.getFirstName() + employee.getLastName())) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(employee.getFirstName() + employee.getLastName());
        return true;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        checkName(firstName, lastName);
        Employee employee = new Employee(firstName, lastName, null, null);
        if(!employees.containsKey(employee.getFirstName() + employee.getLastName())) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    @Override
    public Map<String, Employee> getEmployees() {
        return new HashMap<String, Employee>(employees);
    }


}
