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
    public String transformName(String name) {
        if(StringUtils.isAlpha(name)) {
            return StringUtils.capitalize(StringUtils.lowerCase(name));
        } else{
            throw new IncorrectNameException();
        }

    }

    @Override
    public boolean addEmployee(String firstName, String lastName, Integer salary, Integer department) {
        if (salary < 1) {
            throw new IncorrectSalaryException();
        }
        if (department < 1) {
            throw new IncorrectDepartmentException();
        }
        Employee employee = new Employee(transformName(firstName), transformName(lastName), salary, department);
        if(employees.containsKey(employee.getFirstName() + employee.getLastName())) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(employee.getFirstName() + employee.getLastName(), employee);
        return true;
        }

    @Override
    public boolean removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(transformName(firstName), transformName(lastName), null, null);
        if(!employees.containsKey(employee.getFirstName() + employee.getLastName())) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(employee.getFirstName() + employee.getLastName());
        return true;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(transformName(firstName), transformName(lastName), null, null);
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
