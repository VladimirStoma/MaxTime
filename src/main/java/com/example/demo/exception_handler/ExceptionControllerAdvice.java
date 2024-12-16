package com.example.demo.exception_handler;

import com.example.demo.dto.ResponseEntityException;
import com.example.demo.exeptions.EmployeeRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = {EmployeeRequestException.class})
    public ResponseEntity<ResponseEntityException> handle(EmployeeRequestException ex) {
        ResponseEntityException test = new ResponseEntityException(ex.getMessage());

        return ResponseEntity
                .ok()
                .body(test);
    }
}
