package com.example.demo.service;


import com.example.demo.dto.EmployeeRequest;
import com.example.demo.dto.EmployeeRequest.Employee;
import com.example.demo.dto.EmployeeResponse;
import com.example.demo.exeptions.EmployeeRequestException;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import lombok.RequiredArgsConstructor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RequiredArgsConstructor
class EmployeeServiceTest {
    private final EmployeeService employeeService = new EmployeeService();

    @Test
    void shouldReturnEmployeeResponse() {
        final var employeeRequest = new EmployeeRequest(
            List.of(new Employee(1234, LocalDateTime.of(2023, 3, 23, 10, 25, 8),
                    LocalDateTime.of(2023, 3, 23, 15, 35, 18)),
                new Employee(4535, LocalDateTime.of(2023, 3, 23, 10, 25, 8),
                    LocalDateTime.of(2023, 3, 23, 15, 35, 18)),
                new Employee(1234, LocalDateTime.of(2023, 3, 24, 8, 25, 8),
                    LocalDateTime.of(2023, 3, 24, 19, 0, 18))));


        final var response = employeeService.getEmployeeMaxWorkTime(employeeRequest);
        final var expectedResponse = new EmployeeResponse(1234, "15:45:20");

        assertEquals(expectedResponse, response);
    }

    @Test
    void shouldReturnBadRequestIfStartDateIsWrong() {
        final var exception = assertThrows(
            EmployeeRequestException.class,
            () -> new EmployeeRequest(
                List.of(
                    new Employee(1, LocalDateTime.now().plusDays(1), LocalDateTime.now().minusDays(1))
                ))
        );

        assertEquals("Wrong dateStart. Date start cannot be later than current date", exception.getMessage());
    }
}

