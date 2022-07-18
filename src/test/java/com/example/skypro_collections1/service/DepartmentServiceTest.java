package com.example.skypro_collections1.service;

import com.example.skypro_collections1.Employee;
import com.example.skypro_collections1.exceptions.IncorrectDepartmentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static com.example.skypro_collections1.Constants.DepartmentServiceConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DepartmentServiceTest {

    @Mock
    EmployeeService employeeServiceMock;

    private DepartmentService out;

    @BeforeEach
    public void initOut() {
        out = new DepartmentServiceImpl(employeeServiceMock);

        when(employeeServiceMock.getEmployees())
                .thenReturn(EMPLOYEE_MAP);
    }

    @ParameterizedTest
    @MethodSource("departmentVariants")
    public void shouldCorrectFindMaxSalaryByDepartment(Integer department) {
        if(department < 1) {
            assertThrows(IncorrectDepartmentException.class, () -> out.getEmployeeWithMaxSalaryByDepartment(department));
        } else {
            if(department == 6) {
                assertTrue(out.getEmployeeWithMaxSalaryByDepartment(department).equals(FOURTH_EMPLOYEE));
            } else if(department == 5) {
                assertTrue(out.getEmployeeWithMaxSalaryByDepartment(department).equals(THRD_EMPLOYEE));
            }
        }

    }

    @ParameterizedTest
    @MethodSource("departmentVariants")
    public void shouldCorrectFindMinSalaryByDepartment(Integer department) {
        if(department < 1) {
            assertThrows(IncorrectDepartmentException.class, () -> out.getEmployeeWithMinSalaryByDepartment(department));
        } else {
            if(department == 6) {
                assertTrue(out.getEmployeeWithMinSalaryByDepartment(department).equals(FIRST_EMPLOYEE));
            } else if(department == 5) {
                assertTrue(out.getEmployeeWithMinSalaryByDepartment(department).equals(SCND_EMPLOYEE));
            }
        }

    }

    @ParameterizedTest
    @MethodSource("departmentVariants")
    public void shouldCorrectGetAllEmployeeBYDepartment(Integer department) {
        if(department < 1) {
            assertThrows(IncorrectDepartmentException.class, () -> out.getAllEmployeeByDepartment(department));
        } else {
            Set<Employee> sixSet = new HashSet<>(Set.of(FIRST_EMPLOYEE, FOURTH_EMPLOYEE));
            Set<Employee> fiveSet = new HashSet<>(Set.of(SCND_EMPLOYEE, THRD_EMPLOYEE));
            if(department == 6) {
                assertTrue(sixSet.equals(out.getAllEmployeeByDepartment(department)));
            } else if(department == 5) {
                assertTrue(fiveSet.equals(out.getAllEmployeeByDepartment(department)));
            }
        }

    }


    public static Stream<Arguments> departmentVariants() {
        return Stream.of(
                Arguments.of(6),
                Arguments.of(5),
                Arguments.of(-1)
        );
    }
}