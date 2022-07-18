package com.example.skypro_collections1.service;
import com.example.skypro_collections1.exceptions.EmployeeAlreadyAddedException;
import com.example.skypro_collections1.exceptions.IncorrectDepartmentException;
import com.example.skypro_collections1.exceptions.IncorrectNameException;
import com.example.skypro_collections1.exceptions.IncorrectSalaryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static com.example.skypro_collections1.Constants.EmployeeServiceCostants.*;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    public EmployeeService out;

    @BeforeEach
    public void initObjects() {
        out = new EmployeeServiceImpl();
    }


    @ParameterizedTest
    @MethodSource("provideParamsForTransformName")
    public void shouldTransformNameIsCorrect(String name) {
        if(name.equals(ILLEGAL_CASE_NAME) || name.equals(EMPTY_NAME) || name.equals(ONLY_SPACE_NAME)) {
            assertThrows(IncorrectNameException.class, () -> out.transformName(name));
        } else {
            assertTrue(out.transformName(name).equals(CORRECT_NAME));
        }
    }

    @ParameterizedTest
    @MethodSource("provideCorrectAddedEmployee")
    public void shouldCorrectAddedEmployee(String firstName, String lastName, Integer salary, Integer department) {
        if(salary < 1) {
            assertThrows(IncorrectSalaryException.class, () -> out.addEmployee(firstName, lastName, salary, department));
        } else if (department < 1) {
            assertThrows(IncorrectDepartmentException.class, () -> out.addEmployee(firstName, lastName, salary, department));
        } else {
            String correctFirstName = out.transformName(firstName);
            String correctLastName = out.transformName(lastName);
            if (!out.getEmployees().containsKey(correctFirstName + correctLastName)) {
                out.addEmployee(correctFirstName, correctLastName, salary, department);
                assertTrue(out.getEmployees().containsKey(correctFirstName + correctLastName));
            } else {
                assertThrows(EmployeeAlreadyAddedException.class, () -> out.addEmployee(correctFirstName, correctLastName, salary, department));
            }
        }


    }

    @Test
    public void shouldEmployeeWasAlreadyAdded() {
        out.addEmployee(CORRECT_NAME, CORRECT_NAME, 4, 6);
        assertThrows(EmployeeAlreadyAddedException.class, () -> out.addEmployee(CORRECT_NAME, CORRECT_NAME, 2, 5));
    }

    public static Stream<Arguments> provideParamsForTransformName() {
        return Stream.of(
                Arguments.of(EMPTY_NAME, CORRECT_NAME),
                Arguments.of(ONLY_SPACE_NAME, CORRECT_NAME),
                Arguments.of(LOWER_CASE_NAME, UPPER_CASE_NAME),
                Arguments.of(ILLEGAL_CASE_NAME, CORRECT_NAME),
                Arguments.of(UPPER_CASE_NAME, LOWER_CASE_NAME)
        );
    }

    public static Stream<Arguments> provideCorrectAddedEmployee() {
        return Stream.of(
                Arguments.of(CORRECT_NAME, CORRECT_NAME, 4, 6),
                Arguments.of(LOWER_CASE_NAME, CORRECT_NAME, 4, 1),
                Arguments.of(LOWER_CASE_NAME, UPPER_CASE_NAME, 4, 1),
                Arguments.of(CORRECT_NAME, CORRECT_NAME, 4, 6),
                Arguments.of(CORRECT_NAME, CORRECT_NAME, 3, 4),
                Arguments.of(CORRECT_NAME, CORRECT_NAME, -1, 4),
                Arguments.of(CORRECT_NAME, CORRECT_NAME, 5, 0)
        );
    }
}