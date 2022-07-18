package com.example.skypro_collections1.Constants;

import com.example.skypro_collections1.Employee;

import java.util.HashMap;
import java.util.Map;

public class DepartmentServiceConstants {
    public static final Employee FIRST_EMPLOYEE = new Employee("Andrey", "Vishnevsky", 4, 6);
    public static final Employee SCND_EMPLOYEE = new Employee("Sergey", "Kramsaev", 1, 5);
    public static final Employee THRD_EMPLOYEE = new Employee("Maxim", "Berezin", 56, 5);
    public static final Employee FOURTH_EMPLOYEE = new Employee("Vasily", "Terkin", 55, 6);

    public static final Map<String, Employee> EMPLOYEE_MAP = new HashMap<>(Map.of(
            "Andrey" + "Vishnevsky", FIRST_EMPLOYEE,
            "Sergey" + "Kramsaev", SCND_EMPLOYEE,
            "Maxim" + "Berezin", THRD_EMPLOYEE,
            "Vasily" + "Terkin", FOURTH_EMPLOYEE));
}
