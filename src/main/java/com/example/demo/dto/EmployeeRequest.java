package com.example.demo.dto;

import com.example.demo.exeptions.EmployeeRequestException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties
public record EmployeeRequest(
    List<Employee> employees
) {
    @JsonIgnoreProperties
    public record Employee(
        long id,
        LocalDateTime dateStart,
        LocalDateTime dateEnd
    ) {
        public Employee {
            if (dateStart.isAfter(LocalDateTime.now())) {
                throw new EmployeeRequestException("Wrong dateStart. Date start cannot be later than current date");
            }
        }
    }
}
