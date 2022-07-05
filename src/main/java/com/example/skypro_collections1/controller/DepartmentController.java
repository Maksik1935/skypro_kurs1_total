package com.example.skypro_collections1.controller;

import com.example.skypro_collections1.Employee;
import com.example.skypro_collections1.service.DepartmentService;
import com.example.skypro_collections1.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;

    public DepartmentController(DepartmentService departmentService, EmployeeService employeeService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
    }

    @GetMapping("/max-salary")
    public Employee maxSalary(@RequestParam("department") Integer department) {
        return departmentService.getEmployeeWithMaxSalaryByDepartment(department);
    }
    @GetMapping("/min-salary")
    public Employee minSalary(@RequestParam("department") Integer department) {
        return departmentService.getEmployeeWithMinSalaryByDepartment(department);
    }
    @GetMapping("/all")
    public Set<Employee> getAll(@RequestParam("department") Integer department) {
        return departmentService.getAllEmployeeByDepartment(department);
    }
    @GetMapping("/all")
    public Map<String, String> getAll() {
        return departmentService.getAllEmployees();
    }
}
