package com.example.skypro_collections1.service;

import com.example.skypro_collections1.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getEmployeeWithMaxSalaryByDepartment(Integer department) {

        return employeeService.getEmployees().values().stream()
                .filter(e -> e.getDepartment() == department)
                .sorted(new Comparator<Employee>() {
                    @Override
                    public int compare(Employee o1, Employee o2) {
                        return o1.getSalary() - o2.getSalary();
                    }
                })
                .findFirst()
                .get();
    }
    @Override
    public Employee getEmployeeWithMinSalaryByDepartment(Integer department) {

        return employeeService.getEmployees().values().stream()
                .filter(e -> e.getDepartment() == department)
                .sorted(new Comparator<Employee>() {
                    @Override
                    public int compare(Employee o1, Employee o2) {
                        return o2.getSalary() - o1.getSalary();
                    }
                })
                .findFirst()
                .get();
    }
    @Override
    public Set<Employee> getAllEmployeeByDepartment(Integer department) {

        return employeeService.getEmployees().values().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toSet());
    }
    @Override
    public Map<String, String> getAllEmployees() {

        return employeeService.getEmployees().values().stream()
                .sorted(new Comparator<Employee>() {
                    @Override
                    public int compare(Employee o1, Employee o2) {
                        return o1.getDepartment() - o2.getDepartment();
                    }
                })
                .collect(Collectors.toMap(e -> "Отдел № " + e.getDepartment().toString(), e -> e.getFirstName() + e.getLastName()));
    }

}
