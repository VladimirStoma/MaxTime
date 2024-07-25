package com.example.demo.controller;


import com.example.demo.dto.EmployeeRequest;
import com.example.demo.dto.EmployeeRequest.Employee;
import com.example.demo.dto.EmployeeResponse;
import com.example.demo.service.EmployeeService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import lombok.RequiredArgsConstructor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest
@RequiredArgsConstructor
class EmployeeControllerTest {
    private final EmployeeService employeeService = new EmployeeService();
    private final EmployeeController employeeController = new EmployeeController(employeeService);

    @Test
    void shouldReturnEmployeeResponse() {
        final var employeeRequest = new EmployeeRequest(
            List.of(
                new Employee(1, LocalDateTime.now().minusDays(3), LocalDateTime.now().minusDays(1)),
                new Employee(1234, LocalDateTime.of(2023, 3, 23, 10, 25, 8),
                    LocalDateTime.of(2023, 3, 23, 15, 35, 18)),
                new Employee(4535, LocalDateTime.of(2023, 3, 23, 10, 25, 8),
                    LocalDateTime.of(2023, 3, 23, 15, 35, 18)),
                new Employee(1234, LocalDateTime.of(2023, 3, 24, 8, 25, 8),
                    LocalDateTime.of(2023, 3, 24, 19, 0, 18))));


        final var response = employeeController.getEmployeeWithMaxWorkTime(employeeRequest);

        final var expectedResponse = new EmployeeResponse(1, "48:00:00");
        assertEquals(OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }
}

