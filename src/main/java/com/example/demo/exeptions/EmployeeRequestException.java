package com.example.demo.exeptions;

import lombok.experimental.StandardException;

@StandardException
public class EmployeeRequestException extends RuntimeException{
    public EmployeeRequestException(String message) {
        super(message);
    }
}
