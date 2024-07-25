package com.example.demo.controller;

import com.example.demo.dto.EmployeeRequest;
import com.example.demo.dto.EmployeeResponse;
import com.example.demo.service.EmployeeService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/maxWorkTime")
    public ResponseEntity<EmployeeResponse> getEmployeeWithMaxWorkTime(@RequestBody EmployeeRequest employeeRequest) {
        log.info("Input Request {}", employeeRequest);

        return ResponseEntity.ok()
                   .contentType(MediaType.APPLICATION_JSON)
                   .body(employeeService.getEmployeeMaxWorkTime(employeeRequest));
    }
}
