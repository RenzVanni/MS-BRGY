package com.ms_spring_brgy.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> alreadyExistsException(AlreadyExistsException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }

    @ExceptionHandler(EmptyException.class)
    public ResponseEntity<Map<String, String>> emptyException(EmptyException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(response);
    }

    @ExceptionHandler(DoesNotExistsException.class)
    public ResponseEntity<Map<String, String>> doesNotExistsException(DoesNotExistsException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(response);
    }
}
