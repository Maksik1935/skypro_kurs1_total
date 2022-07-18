package com.example.skypro_collections1.Constants;

import com.example.skypro_collections1.Employee;

import java.util.HashMap;
import java.util.Map;

public class EmployeeServiceCostants {
    public static final String EMPTY_NAME = "";
    public static final String ONLY_SPACE_NAME = "    ";
    public static final String ILLEGAL_CASE_NAME = "John123";
    public static final String CORRECT_NAME = "Andrey";
    public static final String LOWER_CASE_NAME = "andrey";
    public static final String UPPER_CASE_NAME = "ANDREY";
    public static final String CORRECT_MESSAGE = "Well Done, Andrey";

    public static final Employee CORRECT_EMPLOYEE = new Employee(CORRECT_NAME, CORRECT_NAME, 3, 4);
    public static final Employee INCORRECT_EMPLOYEE = new Employee(LOWER_CASE_NAME, CORRECT_NAME, -1, -6);
    public static final Map<String, Employee> EMPTY_MAP = new HashMap<>();
    public static final Map<String, Employee> NOT_EMPTY_MAP = new HashMap<>(Map.of(
            CORRECT_NAME + CORRECT_NAME, CORRECT_EMPLOYEE,
            LOWER_CASE_NAME+CORRECT_NAME, INCORRECT_EMPLOYEE));

}
