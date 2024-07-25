package com.example.demo.service;

import com.example.demo.dto.EmployeeRequest;
import com.example.demo.dto.EmployeeResponse;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class EmployeeService {
    private static final String responseFormat = "%02d:%02d:%02d";

    public EmployeeResponse getEmployeeMaxWorkTime(EmployeeRequest employeeRequest) {
        Map<Long, Duration> workedTime = new HashMap<>();
        final List<EmployeeRequest.Employee> employeeList = employeeRequest.employees();
        for (EmployeeRequest.Employee employeeRequests : employeeList) {
            Duration workDuration = Duration.between(employeeRequests.dateStart(), employeeRequests.dateEnd());
            workedTime.put(employeeRequests.id(), workedTime.getOrDefault(employeeRequests.id(), Duration.ZERO)
                                                      .plus(workDuration));
        }

        Map.Entry<Long, Duration> employeeWithMaxWorkDuration = workedTime.entrySet().stream()
                                                                    .max(Map.Entry.comparingByValue()).orElseThrow();

        Duration duration = employeeWithMaxWorkDuration.getValue();
        long sec = duration.getSeconds();

        return new EmployeeResponse(employeeWithMaxWorkDuration.getKey(),
            String.format(responseFormat, sec / 3600, (sec % 3600) / 60, sec % 60));
    }
}
